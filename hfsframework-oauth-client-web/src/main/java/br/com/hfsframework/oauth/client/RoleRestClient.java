package br.com.hfsframework.oauth.client;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;
import br.com.hfsframework.oauth.client.domain.Role;

@Service
public class RoleRestClient extends BaseRestClient<Role, Long> implements IBaseRestClient<Role, Long> {
	
	private static final Logger log = LoggerFactory.getLogger(RoleRestClient.class);
	
	public RoleRestClient() {
		super();
	}
	
	@Override
	public boolean init(String authServerURL, String accesToken) throws RestClientException {
		return super.init(authServerURL + "/api/v1/role", accesToken, Role.class, 
				new ParameterizedTypeReference<List<Role>>() {});
	}
	
	public Optional<Role> findByName(String name) throws RestClientException {
		try {
			Role obj = restTemplate.getForObject(
					this.server + "/find?name=" + name, Role.class);

			return Optional.of(obj);
			
		} catch (RestClientException e) {			
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}		
	}

	public List<Role> getAll1() throws RestClientException {
		try {
			ResponseEntity<List<Role>> obj = restTemplate.exchange(
					this.server, HttpMethod.GET,
					  null,
					  new ParameterizedTypeReference<List<Role>>(){});
			
			return obj.getBody();
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
	}


	public List<Role> getAll2() throws RestClientException {
		try {
			Role[] obj = restTemplate.getForObject(
					this.server, Role[].class);
			
			return Arrays.asList(obj);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
	}
	
	
}

