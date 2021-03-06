package br.com.hfsframework.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import com.google.common.collect.Sets;

import br.com.hfsframework.base.client.BaseRestTemplateClient;
import br.com.hfsframework.oauth.client.domain.RoleDTO;
import br.com.hfsframework.oauth.client.domain.UserDTO;

//@Controller
public class BaseBecomeMemberController extends BaseRestTemplateClient {

	@Value("${oauth2.hfsframework.server}")
	private String server;

	@Autowired
	private MessageSource messageSource;

	public BaseBecomeMemberController() {
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
			
			UserDTO user = new UserDTO(username, pwd, email, "http://temp", Sets.newHashSet(new RoleDTO("USER")));
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
