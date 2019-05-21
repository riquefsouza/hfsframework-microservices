package br.com.hfsframework.oauth.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.view.BaseViewController;

@Controller
@RequestMapping("/private/changePasswordView")
public class ChangePasswordController extends BaseViewController implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String listPage; 
	
	public ChangePasswordController() {
		this.listPage = "/private/changePassword";
	}

	@GetMapping("/list")
	public String list() {
		return getListPage();
	}
	
	public String getListPage() {
		return listPage;
	}

	public String cancelarEdicao() {
		return getListPage();
	}
	
	public String cancel() {
		return getDesktopPage();
	}

}
