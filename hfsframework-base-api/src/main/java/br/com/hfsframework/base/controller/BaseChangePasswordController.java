package br.com.hfsframework.base.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.oauth.client.domain.UserDTO;

//@Controller
//@RequestMapping("/private/changePassword")
public class BaseChangePasswordController extends BaseViewController {
	
	//private static final Logger log = LoggerFactory.getLogger(ChangePasswordController.class);
	
	private String listPage;
	
	private IBaseUserRestClient restClient;
	
	private UserDTO userLogged;
	
	public BaseChangePasswordController(IBaseUserRestClient restClient) {
		this.listPage = "private/changePassword";
		//this.restClient = new UserRestClient();
		this.restClient = restClient; 
	}

	@GetMapping
	public ModelAndView list() {
		Optional<ModelAndView> mv = getPage(this.listPage);
		
		if (mv.isPresent()) {
			this.restClient.init(authServerURL, accesToken);
			userLogged = restClient.getLoggedUser(getPrincipal());
			mv.get().addObject("user", userLogged);
		}
		
		return mv.get();
	}

	public boolean prepararParaSalvar(UserDTO user, ModelAndView mv) {
		if ((user.getNewPassword() == null && user.getConfirmNewPassword() == null && user.getCurrentPassword() == null)
				|| (user.getNewPassword().equals("") && user.getConfirmNewPassword().equals("") && user.getCurrentPassword().equals(""))) {

			this.showWarningMessage(mv, "changePasswordView.validation");
			
		} else if ((user.getNewPassword() == null && user.getConfirmNewPassword() == null)
				|| (user.getNewPassword().equals("") && user.getConfirmNewPassword().equals(""))) {

			this.showWarningMessage(mv, "changePasswordView.validation");
			
		} else {

			if (BCrypt.checkpw(user.getCurrentPassword(), user.getPassword())) {

				if (user.getNewPassword().equals(user.getConfirmNewPassword())) {
					return true;
				} else {
					this.showWarningMessage(mv, "changePasswordView.notEqual");					
				}
			} else {
				this.showWarningMessage(mv, "changePasswordView.currentPwdNotEqual");
			}
		}
		return false;
	}
	
	@PostMapping
	public ModelAndView save(@Valid UserDTO user, 
			BindingResult result, RedirectAttributes attributes) {
		Optional<ModelAndView> mv = getPage(this.listPage);
		mv.get().addObject("user", user);

		if (result.hasErrors()){
			//this.showWarningMessage(mv.get(), "changePasswordView.checkFields");
			//logBindingResultErrors(result, log);
			return mv.get();
		}

		if (!prepararParaSalvar(user, mv.get())){
			return mv.get();
		}
		
		try {
			String pwdCrypt = BCrypt.hashpw(user.getConfirmNewPassword(), BCrypt.gensalt());
			
			userLogged.setPassword(pwdCrypt);
			
			restClient.updateById(userLogged);
			
			this.showWarningMessage(mv.get(), "changePasswordView.passwordChanged");
		} catch (RestClientException e) {
			this.showDangerMessage(mv.get(), e);
			return mv.get();
		}
		
		return mv.get();
	}
	
}
