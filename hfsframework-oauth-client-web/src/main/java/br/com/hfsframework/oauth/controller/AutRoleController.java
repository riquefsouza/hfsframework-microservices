package br.com.hfsframework.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.view.BaseViewRegisterRestClient;
import br.com.hfsframework.oauth.client.RoleRestClient;
import br.com.hfsframework.oauth.client.domain.Role;

@Controller
@RequestMapping(value = "/private/roleView")
public class AutRoleController extends BaseViewRegisterRestClient<Role, Long, RoleRestClient> {

	private static final long serialVersionUID = 1L;

	public AutRoleController() {
		super(new RoleRestClient(), "/private/autRole/listAutRole", "/private/autRole/editAutRole", "AutRole");
	}
	
}
