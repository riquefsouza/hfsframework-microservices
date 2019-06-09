package br.com.hfsframework.admin.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.admin.client.AdmParameterRestClient;
import br.com.hfsframework.admin.client.domain.AdmParameter;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/admParameterView")
public class AdmParameterController extends BaseViewRegisterRestClient<AdmParameter, Long, AdmParameterRestClient> {

	private static final long serialVersionUID = 1L;

	public AdmParameterController() {
		super(new AdmParameterRestClient(), 
				"/private/admParameter/listAdmParameter", 
				"/private/admParameter/editAdmParameter", 
				"AdmParameter");
	}

	@PostConstruct
	public void init() {
		//
	}
}
