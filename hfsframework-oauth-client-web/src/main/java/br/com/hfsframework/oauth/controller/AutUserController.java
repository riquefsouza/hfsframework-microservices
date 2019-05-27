package br.com.hfsframework.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.view.BaseViewRegisterRestClient;
import br.com.hfsframework.oauth.client.UserRestClient;
import br.com.hfsframework.oauth.client.domain.User;

@Controller
@RequestMapping(value = "/private/userView")
public class AutUserController extends BaseViewRegisterRestClient<User, Long, UserRestClient> {

	private static final long serialVersionUID = 1L;

	public AutUserController() {
		super("/private/autRole/listAutUser", "/private/autRole/editAutUser", "AutUser");
	}

}
