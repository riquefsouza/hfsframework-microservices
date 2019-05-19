package br.com.hfsframework.security.login;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hfsframework.base.client.BaseOAuth2RestTemplateClient;

@RestController
@CrossOrigin
public class Login2RestController extends BaseOAuth2RestTemplateClient {
	
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/api/public/login2", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> autentica(@RequestBody LoginDTO loginDTO) {
		try {
			String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
			
			/*
			OAuth2RestTemplate oauth2RestTemplate = this.restTemplate(baseURL + "/oauth/token", 
					loginDTO.getLogin(), loginDTO.getSenha());
			String sToken = oauth2RestTemplate.getAccessToken().getValue();
			
			return ResponseEntity.ok(sToken);
			*/
						
			RequestEntity<LoginDTO> request = RequestEntity
					.post(URI.create(baseURL + "/api/login"))
					.contentType(MediaType.APPLICATION_JSON).body(loginDTO);

			ResponseEntity<String> response = restTemplate.exchange(request, String.class);
				return ResponseEntity.ok(response.getHeaders().get(TokenAuthenticationService.AUTH_HEADER_NAME).get(0));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}
}
