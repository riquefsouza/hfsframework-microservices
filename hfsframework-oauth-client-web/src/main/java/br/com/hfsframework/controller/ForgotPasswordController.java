package br.com.hfsframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.client.BaseRestTemplateClient;

@Controller
public class ForgotPasswordController extends BaseRestTemplateClient {

	@Value("${oauth2.hfsframework.server}")
	private String server;

	@Autowired
	private MessageSource messageSource;

	public ForgotPasswordController() {
		super();
	}

	@GetMapping("/public/forgotPassword")
	@ResponseBody
	public String send(@RequestParam(name = "username", required = true) String username) {

		String subject = messageSource.getMessage("login.forgotPassword", null, LocaleContextHolder.getLocale());
		String text = messageSource.getMessage("forgotPassword.textMail", null, LocaleContextHolder.getLocale());

		try {
			String url = this.server + "/api/public/forgotPassword?username=" + username + 
					"&subject=" + subject + "&text=" + text;

			ResponseEntity<String> obj = restTemplate().getForEntity(url, String.class);

			if (obj.getBody().equals(username) && obj.getStatusCode().equals(HttpStatus.OK)) {
				return messageSource.getMessage("forgotPassword.sendMail", null, LocaleContextHolder.getLocale());
			} else {
				return messageSource.getMessage("forgotPassword.noSendMail", null, LocaleContextHolder.getLocale());
			}

		} catch (RestClientException e) {
			return "Error: " + e.getMessage();
		}

	}

}
