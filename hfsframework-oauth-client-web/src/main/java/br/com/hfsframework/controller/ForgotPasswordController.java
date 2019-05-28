package br.com.hfsframework.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.oauth.client.UserRestClient;
import br.com.hfsframework.oauth.client.domain.User;
import br.com.hfsframework.useful.mail.MailUtil;

@Controller
public class ForgotPasswordController extends BaseViewController {

	public ForgotPasswordController() {
		super();
	}

	@Autowired
	private MailUtil mailUtil;

	@PostMapping("/public/forgotPassword")
	public String send(@RequestParam(name = "username", required = true) String username) {
		
		String subject =  messageSource.getMessage("login.forgotPassword", null, Locale.getDefault());
		String text = "hfs framework";
		
		try {
			UserRestClient restClient = new UserRestClient(this.authServerURL, this.accesToken);
			Optional<User> user = restClient.findByUsername(username);
			
			if (user.isPresent()) {
				mailUtil.sendSimpleMessage(user.get().getEmail(), subject, text);
			} else {		
				user = restClient.findByEmail(username);
				
				if (user.isPresent()) {
					mailUtil.sendSimpleMessage(user.get().getEmail(), subject, text);
				} else {
					//this.showErrorMessage(attributes, e);
					return messageSource.getMessage("forgotPassword.noSendMail", null, Locale.getDefault());
				}			
			}
		} catch (RestClientException e) {
			return "Error: " + e.getMessage();
		}
		
		return "/login";
	}
	
}
