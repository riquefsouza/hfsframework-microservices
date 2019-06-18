package br.com.hfsframework.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hfsframework.admin.client.AdmPageRestClient;
import br.com.hfsframework.admin.client.AdmProfileRestClient;
import br.com.hfsframework.admin.client.domain.AdmPageDTO;
import br.com.hfsframework.admin.client.domain.AdmProfileDTO;
import br.com.hfsframework.base.BaseDualList;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/admPageView")
public class AdmPageController extends BaseViewRegisterRestClient<AdmPageDTO, Long, AdmPageRestClient> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AdmProfileRestClient admProfileClient;
	
	private BaseDualList<AdmProfileDTO> dualListProfile;
	
	private List<AdmProfileDTO> listAllAdmProfiles;
	
	public AdmPageController() {
		super(new AdmPageRestClient(), 
				"/private/admPage/listAdmPage", 
				"/private/admPage/editAdmPage", 
				"AdmPage");
		this.listAllAdmProfiles = new ArrayList<AdmProfileDTO>();
	}
	
	@PostConstruct
	public void init() {
		//
	}
	private void carregarProfiles(AdmPageDTO bean, boolean bEdit) {
		List<AdmProfileDTO> listProfilesSelected;		 
		List<AdmProfileDTO> listProfiles = admProfileClient.getAll();
		listAllAdmProfiles.addAll(listProfiles);
		
		if (bEdit) { 
			listProfilesSelected = new ArrayList<AdmProfileDTO>();
			
			/*
			bean.getAdmProfiles().forEach(id -> {
				listProfilesSelected.add(listAllAdmProfiles.stream()
						.filter(p -> p.getId().equals(id))
						.findFirst().get());	
			});
			*/
			
			for (AdmProfileDTO profile : listAllAdmProfiles) {
				for (AdmPageDTO page : profile.getAdmPages()) {
					if (page.equals(bean)) {
						listProfilesSelected.add(profile);
						break;
					}
				}
			}
			
			
			listProfiles.removeAll(listProfilesSelected);
		} else {
			listProfilesSelected = new ArrayList<AdmProfileDTO>();
		}
		
		this.dualListProfile = new BaseDualList<AdmProfileDTO>(listProfiles, listProfilesSelected);		
	}
	
	@Override
	@GetMapping("/add")	
	public ModelAndView add(AdmPageDTO bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());
		bean.clear();
					
		if (mv.isPresent()) {
			admProfileClient.init(this.resourceServer, this.accesToken);
			carregarProfiles(bean, false);
			mv.get().addObject("listSourceProfiles", this.dualListProfile.getSource());
			mv.get().addObject("bean", bean);
		}
		
		return mv.get();
	}

	@Override
	@GetMapping("/edit")
	public ModelAndView edit(AdmPageDTO bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());

		Optional<AdmPageDTO> obj = bean.fromJSON();
		if (obj.isPresent()) {
			bean = obj.get();
		
			if (mv.isPresent()) {
				admProfileClient.init(this.resourceServer, this.accesToken);
				carregarProfiles(bean, true);
				mv.get().addObject("listSourceProfiles", this.dualListProfile.getSource());
				mv.get().addObject("listTargetProfiles", this.dualListProfile.getTarget());
				mv.get().addObject("bean", bean);
			}
		}
		
		return mv.get();
	}

	@Override
	@PostMapping
	public ModelAndView save(@Valid @ModelAttribute AdmPageDTO bean, 
			BindingResult result, RedirectAttributes attributes) {
	
		Optional<ModelAndView> mv = getPage(this.getListPage());
		
		return this.saveCallableBefore(bean, result, attributes, new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				
				if (mv.isPresent()) {
					/*
					bean.getAdmProfiles().forEach(item -> {					
						listAllAdmProfiles.stream()
							.filter(p -> p.getId().equals(item))
							.findFirst();	
					});
					*/
					mv.get().addObject("bean", bean);
				}
				
				return "";
			}
		});
	}
		
}
