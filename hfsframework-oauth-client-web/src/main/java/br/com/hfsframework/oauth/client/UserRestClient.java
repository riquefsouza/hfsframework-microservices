package br.com.hfsframework.oauth.client;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.oauth.client.domain.User;

public class UserRestClient extends BaseRestClient<User, Long> {

	private static final Logger log = LoggerFactory.getLogger(UserRestClient.class);
	
	public UserRestClient(String authServerURL, String sAccesToken) {
		super.init(authServerURL + "/api/v1/user", sAccesToken, User.class);
	}
	
	public Optional<User> findByUsername(String username) {
		try {
			User obj = restTemplate.getForObject(
					this.server + "/find?username=" + username, User.class);

			return Optional.of(obj);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
		
		return Optional.empty();
	}

	public User getLoggedUser(Optional<BaseOAuth2RestUser> principal) {
		if (principal.isPresent()) {
			String username = principal.get().getUsername();

			Optional<User> user = this.findByUsername(username);
			
			if (user.isPresent()) {
				return user.get();
			}
		}
		
		return null;
	}
	
	
}
