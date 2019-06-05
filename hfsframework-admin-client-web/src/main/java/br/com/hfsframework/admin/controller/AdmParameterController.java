package br.com.hfsframework.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.view.BaseViewRegisterRestClient;
import br.com.hfsframework.admin.client.AdmParameterRestClient;
import br.com.hfsframework.admin.client.domain.AdmParameter;

@Controller
@RequestMapping(value = "/private/admParameterView")
public class AdmParameterController extends BaseViewRegisterRestClient<AdmParameter, Long, AdmParameterRestClient> {

	private static final long serialVersionUID = 1L;

	public AdmParameterController() {
		super(new AdmParameterRestClient(), "/private/admParameter/listAdmParameter", "/private/admParameter/editAdmParameter", "AdmParameter");
	}
	
}
