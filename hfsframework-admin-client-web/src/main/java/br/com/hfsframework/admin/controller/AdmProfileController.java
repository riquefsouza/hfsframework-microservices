package br.com.hfsframework.admin.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.admin.client.AdmProfileRestClient;
import br.com.hfsframework.admin.client.domain.AdmProfile;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/admProfileView")
public class AdmProfileController extends BaseViewRegisterRestClient<AdmProfile, Long, AdmProfileRestClient> {

	private static final long serialVersionUID = 1L;
	
	public AdmProfileController() {
		super(new AdmProfileRestClient(), 
				"/private/admProfile/listAdmProfile", 
				"/private/admProfile/editAdmProfile", 
				"AdmProfile");
	}

	@PostConstruct
	public void init() {
		//
	}
}
