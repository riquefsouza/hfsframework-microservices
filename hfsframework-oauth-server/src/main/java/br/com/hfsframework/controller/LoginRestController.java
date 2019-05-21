package br.com.hfsframework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hfsframework.base.client.BaseRestTemplateClient;
import br.com.hfsframework.base.security.BaseRestUser;
import br.com.hfsframework.security.login.LoginDTO;

@RestController
@CrossOrigin
public class LoginRestController extends BaseRestTemplateClient {
		
	@PostMapping(value = "/api/public/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseRestUser> autentica(@RequestBody LoginDTO loginDTO) {
		try {
			String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
			
			BaseRestUser baseUser = this.login(baseURL + "/oauth/token", 
					loginDTO.getLogin(), loginDTO.getSenha());
			
			return ResponseEntity.ok(baseUser);
						
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}
}
