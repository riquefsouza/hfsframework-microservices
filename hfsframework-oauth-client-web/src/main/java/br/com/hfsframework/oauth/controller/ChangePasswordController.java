package br.com.hfsframework.oauth.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.oauth.client.UserRestClient;
import br.com.hfsframework.oauth.client.domain.User;

@Controller
@RequestMapping("/private/changePasswordView")
public class ChangePasswordController extends BaseViewController implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private String listPage;
	
	private User userLogged;
	
	private UserRestClient restClient;
	
	public ChangePasswordController() {
		this.listPage = "/private/changePasswordView/list";
	}

	@GetMapping("/list")
	public ModelAndView list() {
		this.userLogged = restClient.getLoggedUser(getPrincipal());
		ModelAndView mv = new ModelAndView(getListPage());
		mv.addObject("bean", userLogged);
		return mv;
	}
	
	public boolean prepararParaSalvar(User obj, RedirectAttributes attributes) {
		if ((obj.getNewPassword() == null && obj.getConfirmNewPassword() == null && obj.getCurrentPassword() == null)
				|| (obj.getNewPassword().equals("") && obj.getConfirmNewPassword().equals("") && obj.getCurrentPassword().equals(""))) {

			this.showValidationMessage(attributes, "changePasswordView.validation");
			
		} else if ((obj.getNewPassword() == null && obj.getConfirmNewPassword() == null)
				|| (obj.getNewPassword().equals("") && obj.getConfirmNewPassword().equals(""))) {

			this.showValidationMessage(attributes, "changePasswordView.validation");
			
		} else {
			String senha = BCrypt.hashpw(obj.getCurrentPassword(), BCrypt.gensalt());
			
			if (senha.equals(this.userLogged.getPassword())) {

				if (obj.getNewPassword().equals(obj.getConfirmNewPassword())) {
					return true;
				} else {
					this.showValidationMessage(attributes, "changePasswordView.notEqual");					
				}
			} else {
				this.showValidationMessage(attributes, "changePasswordView.currentPwdNotEqual");
			}
		}
		return false;
	}
	
	@PostMapping("/save")
	public RedirectView save(@Valid User obj, 
			BindingResult result, RedirectAttributes attributes) {
		
		if (prepararParaSalvar(obj, attributes)){
			return new RedirectView(getListPage());
		}
		
		if (result.hasErrors()){
			this.showValidationMessage(attributes, "changePasswordView.checkFields");
			return new RedirectView(getListPage());
		}
		
		try {
			String password = BCrypt.hashpw(obj.getConfirmNewPassword(), BCrypt.gensalt());
						
			//UsuarioAutenticadoVO usuarioAut = getUsuarioAutenticado();
			//usuarioAut.getUsuario().setSenha(password);
			this.userLogged.setPassword(password);
			
			
			//getBusinessController().updateSenha(usuarioAut.getUsuario().getSenha(), 
				//	usuarioAut.getUsuario().getLogin());
			
			restClient.updateById(userLogged);
			
			//setUsuarioAutenticado(usuarioAut);
			
			this.showValidationMessage(attributes, "changePasswordView.passwordChanged");
		} catch (Exception e) {
			this.showErrorMessage(attributes, e);
			return new RedirectView(getListPage());
		}
		
		return new RedirectView(getListPage());		
	}	
	
	public String getListPage() {
		return listPage;
	}

	public String cancelEdition() {
		return getListPage();
	}
	
	public String cancel() {
		return getDesktopPage();
	}

}
