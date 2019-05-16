package br.com.hfsframework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hfsframework.base.BaseOAuth2RestTemplateClient;
import br.com.hfsframework.domain.LoginDTO;

@RestController
@CrossOrigin
public class LoginRestController extends BaseOAuth2RestTemplateClient {
	
	@PostMapping(value = "/api/public/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> autentica(HttpServletRequest request, @RequestBody LoginDTO loginDTO) {
		try {
			String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
			
			OAuth2RestTemplate oauth2RestTemplate = this.restTemplate(baseURL + "/oauth/token", 
					loginDTO.getLogin(), loginDTO.getSenha());
			String sToken = oauth2RestTemplate.getAccessToken().getValue();
			
			return ResponseEntity.ok(sToken);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}
}
