package br.com.hfsframework.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.view.BaseViewRegisterRestClient;
import br.com.hfsframework.admin.client.AdmMenuRestClient;
import br.com.hfsframework.admin.client.domain.AdmMenu;

@Controller
@RequestMapping(value = "/private/admMenuView")
public class AdmMenuController extends BaseViewRegisterRestClient<AdmMenu, Long, AdmMenuRestClient> {

	private static final long serialVersionUID = 1L;

	public AdmMenuController() {
		super(new AdmMenuRestClient(), "/private/admMenu/listAdmMenu", "/private/admMenu/editAdmMenu", "AdmMenu");
	}
	
}
