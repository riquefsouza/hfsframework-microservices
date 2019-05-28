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
import br.com.hfsframework.base.security.DecodedJwt;
import br.com.hfsframework.oauth.dto.LoginDTO;

@RestController
@CrossOrigin
public class LoginRestController extends BaseRestTemplateClient {
		
	@PostMapping(value = "/api/public/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseRestUser> autentica(@RequestBody LoginDTO loginDTO) {
		try {
			String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
			BaseRestUser user = null;
			BaseRestUser baseUser = this.login(baseURL, 
					loginDTO.getLogin(), loginDTO.getPassword());
			
			if (baseUser.isAuthenticated()) {
				String sToken = baseUser.getAccessToken();
				DecodedJwt decodedJwt = DecodedJwt.getDecodedJwt(sToken);
				String[] roles = new String[decodedJwt.getAuthorities().size()];
				roles = decodedJwt.getAuthorities().toArray(roles);
				
				user = new BaseRestUser(baseUser, decodedJwt.getEmail(), decodedJwt.getUrlPhoto(), roles);
			}
			
			if (user==null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			
			return ResponseEntity.ok(user);
						
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}
}
