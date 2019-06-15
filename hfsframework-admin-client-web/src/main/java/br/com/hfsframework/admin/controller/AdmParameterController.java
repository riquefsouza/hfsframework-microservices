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

import br.com.hfsframework.admin.client.AdmParameterCategoryRestClient;
import br.com.hfsframework.admin.client.AdmParameterRestClient;
import br.com.hfsframework.admin.client.domain.AdmParameterCategoryDTO;
import br.com.hfsframework.admin.client.domain.AdmParameterDTO;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/admParameterView")
public class AdmParameterController extends BaseViewRegisterRestClient<AdmParameterDTO, Long, AdmParameterRestClient> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AdmParameterCategoryRestClient admParameterCategoryClient;

	private List<AdmParameterCategoryDTO> listAllAdmParameterCategories;
	
	public AdmParameterController() {
		super(new AdmParameterRestClient(), 
				"/private/admParameter/listAdmParameter", 
				"/private/admParameter/editAdmParameter", 
				"AdmParameter");
		this.listAllAdmParameterCategories = new ArrayList<AdmParameterCategoryDTO>();
	}

	@PostConstruct
	public void init() {
		//
	}
	
	@Override
	@GetMapping("/add")	
	public ModelAndView add(AdmParameterDTO bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());
		bean.clear();
				
		if (mv.isPresent()) {
			admParameterCategoryClient.init(this.resourceServer, this.accesToken);
			listAllAdmParameterCategories = admParameterCategoryClient.getAll();
			mv.get().addObject("listAdmCategories", listAllAdmParameterCategories);
			mv.get().addObject("bean", bean);
		}
		
		return mv.get();
	}
	
	@Override
	@GetMapping("/edit")
	public ModelAndView edit(AdmParameterDTO bean) {
		Optional<ModelAndView> mv = getPage(getEditPage());

		Optional<AdmParameterDTO> obj = bean.fromJSON();
		if (obj.isPresent()) {
			bean = obj.get();
			
			if (mv.isPresent()) {
				admParameterCategoryClient.init(this.resourceServer, this.accesToken);
				listAllAdmParameterCategories = admParameterCategoryClient.getAll();
				mv.get().addObject("listAdmCategories", listAllAdmParameterCategories);
				mv.get().addObject("bean", bean);
			}
		}
		
		return mv.get();
	}
	
	@Override
	@PostMapping
	public ModelAndView save(@Valid @ModelAttribute AdmParameterDTO bean, 
			BindingResult result, RedirectAttributes attributes) {
	
		Optional<ModelAndView> mv = getPage(this.getListPage());
		
		return this.saveCallableBefore(bean, result, attributes, new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				
				if (mv.isPresent()) {

					listAllAdmParameterCategories.stream()
					.filter(p -> p.getId().equals(bean.getAdmParameterCategory().getId()))
					.findFirst()
					.ifPresent(x -> bean.getAdmParameterCategory().setDescription(x.getDescription()));
					
					mv.get().addObject("bean", bean);
				}
				
				return "";
			}
		});
	}
	
}
