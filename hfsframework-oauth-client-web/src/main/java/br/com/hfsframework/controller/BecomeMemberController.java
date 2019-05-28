package br.com.hfsframework.controller;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.view.BaseViewController;
import br.com.hfsframework.oauth.client.RoleRestClient;
import br.com.hfsframework.oauth.client.UserRestClient;
import br.com.hfsframework.oauth.client.domain.Role;
import br.com.hfsframework.oauth.client.domain.User;

@Controller
public class BecomeMemberController extends BaseViewController {

	public BecomeMemberController() {
		super();
	}

	@PostMapping("/public/becomeMember")
	public String signUp(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "new", required = true) String newPassword,
			@RequestParam(name = "confirm", required = true) String confirmNewPassword) {

		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String pwd = passwordEncoder.encode(confirmNewPassword);

			RoleRestClient roleClient = new RoleRestClient(this.authServerURL, this.accesToken);
			
			Optional<Role> userRole = roleClient.findByName("USER");
			
			if (userRole.isPresent()) {		
				User user = new User(username, pwd, email, "http://temp", Arrays.asList(userRole.get()));
				
				UserRestClient userClient = new UserRestClient(this.authServerURL, this.accesToken);
				Optional<User> newUser = userClient.add(user);
						
				if (newUser.isPresent()) {
					
				} else {
					//this.showErrorMessage(attributes, e);
					return messageSource.getMessage("dlgBecomeMember.noSave", null, Locale.getDefault());
				}
				
			} else {
				//this.showErrorMessage(attributes, e);
				return messageSource.getMessage("dlgBecomeMember.noRole", null, Locale.getDefault());
			}
		} catch (RestClientException e) {
			return "Error: " + e.getMessage();
		}
		
		
		return "/login";
	}

}
