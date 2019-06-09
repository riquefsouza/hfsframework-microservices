package br.com.hfsframework.admin.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.admin.client.AdmUserRestClient;
import br.com.hfsframework.admin.client.domain.AdmUser;
import br.com.hfsframework.base.view.BaseViewRegisterRestClient;

@Controller
@RequestMapping(value = "/private/admUserView")
public class AdmUserController extends BaseViewRegisterRestClient<AdmUser, Long, AdmUserRestClient> {

	private static final long serialVersionUID = 1L;

	public AdmUserController() {
		super(new AdmUserRestClient(), 
				"/private/admUser/listAdmUser", 
				"/private/admUser/editAdmUser", 
				"AdmUser");
	}

	@PostConstruct
	public void init() {
		//
	}
}
