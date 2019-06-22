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
import br.com.hfsframework.admin.client.AdmUserRestClient;
import br.com.hfsframework.admin.client.domain.AdmPageDTO;
import br.com.hfsframework.admin.client.domain.AdmProfileDTO;
import br.com.hfsframework.admin.client.domain.AdmUserDTO;
import br.com.hfsframework.base.BaseDualList;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/admProfileView")
public class AdmProfileController extends BaseViewRegisterRestClient<AdmProfileDTO, Long, AdmProfileRestClient> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdmPageRestClient admPageClient;
	
	private BaseDualList<AdmPageDTO> dualListAdmPage;
	
	private List<AdmPageDTO> listAllAdmPages;
	
	@Autowired
	private AdmUserRestClient admUserClient;
	
	private BaseDualList<AdmUserDTO> dualListAdmUser;
	
	private List<AdmUserDTO> listAllAdmUsers;	
	
	public AdmProfileController() {
		super(new AdmProfileRestClient(), 
				"/private/admProfile/listAdmProfile", 
				"/private/admProfile/editAdmProfile", 
				"AdmProfile");
		this.listAllAdmPages = new ArrayList<AdmPageDTO>();
		this.listAllAdmUsers = new ArrayList<AdmUserDTO>();
	}

	@PostConstruct
	public void init() {
		//
	}
	
	private void carregarAdmPages(AdmProfileDTO bean, boolean bEdit) {
		List<AdmPageDTO> listAdmPagesSelected;		 
		List<AdmPageDTO> listAdmPages = admPageClient.getAll();
		listAllAdmPages.addAll(listAdmPages);
		
		if (bEdit) { 
			listAdmPagesSelected = new ArrayList<AdmPageDTO>(bean.getAdmPages());
			listAdmPages.removeAll(listAdmPagesSelected);
		} else {
			listAdmPagesSelected = new ArrayList<AdmPageDTO>();
		}
		
		this.dualListAdmPage = new BaseDualList<AdmPageDTO>(listAdmPages, listAdmPagesSelected);		
	}
	
	private void carregarAdmUsers(AdmProfileDTO bean, boolean bEdit) {
		List<AdmUserDTO> listAdmUsersSelected;		 
		List<AdmUserDTO> listAdmUsers = admUserClient.getAll();
		listAllAdmUsers.addAll(listAdmUsers);
		
		if (bEdit) { 
			listAdmUsersSelected = new ArrayList<AdmUserDTO>(bean.getAdmUsers());
			listAdmUsers.removeAll(listAdmUsersSelected);
		} else {
			listAdmUsersSelected = new ArrayList<AdmUserDTO>();
		}
		
		this.dualListAdmUser = new BaseDualList<AdmUserDTO>(listAdmUsers, listAdmUsersSelected);		
	}	
	
	@Override
	@GetMapping("/add")	
	public ModelAndView add(AdmProfileDTO bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());
		bean.clear();
				
		if (mv.isPresent()) {
			admPageClient.init(this.resourceServer, this.accesToken);
			carregarAdmPages(bean, false);
			mv.get().addObject("listSourceAdmPages", this.dualListAdmPage.getSource());

			admUserClient.init(this.resourceServer, this.accesToken);
			carregarAdmUsers(bean, false);
			mv.get().addObject("listSourceAdmUsers", this.dualListAdmUser.getSource());
			
			mv.get().addObject("bean", bean);
		}
		
		return mv.get();
	}
	
	@Override
	@GetMapping("/edit")
	public ModelAndView edit(AdmProfileDTO bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());

		Optional<AdmProfileDTO> obj = bean.fromJSON();
		if (obj.isPresent()) {
			bean = obj.get();
			
			if (mv.isPresent()) {
				admPageClient.init(this.resourceServer, this.accesToken);
				carregarAdmPages(bean, true);
				mv.get().addObject("listSourceAdmPages", this.dualListAdmPage.getSource());
				mv.get().addObject("listTargetAdmPages", this.dualListAdmPage.getTarget());

				admUserClient.init(this.resourceServer, this.accesToken);
				carregarAdmUsers(bean, true);
				mv.get().addObject("listSourceAdmUsers", this.dualListAdmUser.getSource());
				mv.get().addObject("listTargetAdmUsers", this.dualListAdmUser.getTarget());
				
				mv.get().addObject("bean", bean);
			}
		}
		
		return mv.get();
	}
	
	@Override
	@PostMapping
	public ModelAndView save(@Valid @ModelAttribute AdmProfileDTO bean, 
			BindingResult result, RedirectAttributes attributes) {
	
		Optional<ModelAndView> mv = getPage(this.getListPage());
		
		return this.saveCallableBefore(bean, result, attributes, new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				
				if (mv.isPresent()) {
					
					bean.getAdmPages().forEach(item -> {
						listAllAdmPages.stream()
							.filter(p -> p.getId().equals(item.getId()))
							.findFirst()
							.ifPresent(x -> item.setDescription(x.getDescription()));	
					});
					bean.getAdmUsers().forEach(item -> {
						listAllAdmUsers.stream()
							.filter(p -> p.getId().equals(item.getId()))
							.findFirst()
							.ifPresent(x -> item.setName(x.getName()));	
					});
					
					mv.get().addObject("bean", bean);
				}
				
				return "";
			}
		});
	}	
}
