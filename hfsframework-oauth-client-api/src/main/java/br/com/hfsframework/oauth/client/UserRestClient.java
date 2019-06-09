package br.com.hfsframework.oauth.client;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.controller.IBaseUserRestClient;
import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.oauth.client.domain.User;

@Service
public class UserRestClient extends BaseRestClient<User, Long> implements IBaseUserRestClient {

	private static final Logger log = LoggerFactory.getLogger(UserRestClient.class);
	
	public UserRestClient() {
		super();
	}

	@Override
	public boolean init(String serverURL, String sAccesToken) throws RestClientException {
		return super.init(serverURL + "/api/v1/user", sAccesToken, User.class,
				new ParameterizedTypeReference<List<User>>() {});
	}
	
	public Optional<User> findByUsername(String username) throws RestClientException {
		try {
			User obj = restTemplate.getForObject(
					this.server + "/find?username=" + username, User.class);

			return Optional.of(obj);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
		//return Optional.empty();
	}

	public User getLoggedUser(Optional<BaseOAuth2RestUser> principal) throws RestClientException {
		if (principal.isPresent()) {
			String username = principal.get().getUsername();

			Optional<User> user = this.findByUsername(username);
			
			if (user.isPresent()) {
				return user.get();
			}
		}
		
		return null;
	}
	
	public Optional<User> findByEmail(String email) throws RestClientException {
		try {
			User obj = restTemplate.getForObject(
					this.server + "/findemail?email=" + email, User.class);

			return Optional.of(obj);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
		//return Optional.empty();
	}

}
