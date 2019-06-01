package br.com.hfsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.controller.BaseChangePasswordController;
import br.com.hfsframework.base.controller.IBaseUserRestClient;
import br.com.hfsframework.oauth.client.UserRestClient;

@Controller
@RequestMapping("/private/changePassword")
public class ChangePasswordController extends BaseChangePasswordController {

	public ChangePasswordController(IBaseUserRestClient restClient) {
		super(new UserRestClient());
	}
	
}
