package br.com.hfsframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import com.google.common.collect.Sets;

import br.com.hfsframework.base.client.BaseRestTemplateClient;
import br.com.hfsframework.oauth.client.domain.Role;
import br.com.hfsframework.oauth.client.domain.User;

@Controller
public class BecomeMemberController extends BaseRestTemplateClient {

	@Value("${oauth2.hfsframework.server}")
	private String server;

	@Autowired
	private MessageSource messageSource;

	public BecomeMemberController() {
		super();
	}

	@GetMapping("/public/becomeMember")
	@ResponseBody
	public String signUp(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "new", required = true) String newPassword,
			@RequestParam(name = "confirm", required = true) String confirmNewPassword) {

		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String pwd = passwordEncoder.encode(confirmNewPassword);
			
			User user = new User(username, pwd, email, "http://temp", Sets.newHashSet(new Role("USER")));
			user.setCurrentPassword(pwd);
			user.setNewPassword(newPassword);
			user.setConfirmNewPassword(confirmNewPassword);

			String url = this.server + "/api/public/signup";

			ResponseEntity<String> obj = restTemplate().postForEntity(url, user, String.class);

			if (obj.getStatusCode().equals(HttpStatus.OK)) {
				
				//String userId = obj.getBody();
				
				return messageSource.getMessage("dlgBecomeMember.okSave", null, LocaleContextHolder.getLocale());
			} else
				return messageSource.getMessage("dlgBecomeMember.noSave", null, LocaleContextHolder.getLocale());

		} catch (RestClientException e) {
			return "Error: " + e.getMessage();
		}

	}

}
