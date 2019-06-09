package br.com.hfsframework.admin.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.admin.client.AdmPageRestClient;
import br.com.hfsframework.admin.client.domain.AdmPage;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/admPageView")
public class AdmPageController extends BaseViewRegisterRestClient<AdmPage, Long, AdmPageRestClient> {

	private static final long serialVersionUID = 1L;

	public AdmPageController() {
		super(new AdmPageRestClient(), 
				"/private/admPage/listAdmPage", 
				"/private/admPage/editAdmPage", 
				"AdmPage");
	}
	
	@PostConstruct
	public void init() {
		//
	}
}
