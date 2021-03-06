package br.com.hfsframework.oauth.client;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;
import br.com.hfsframework.oauth.client.domain.RoleDTO;

@Service
public class RoleRestClient extends BaseRestClient<RoleDTO, Long> implements IBaseRestClient<RoleDTO, Long> {
	
	private static final Logger log = LoggerFactory.getLogger(RoleRestClient.class);
	
	public RoleRestClient() {
		super();
	}
	
	@Override
	public boolean init(String serverURL, String accesToken) throws RestClientException {
		return super.init(serverURL + "/api/v1/role", accesToken, RoleDTO.class, 
				new ParameterizedTypeReference<List<RoleDTO>>() {});
	}
	
	public Optional<RoleDTO> findByName(String name) throws RestClientException {
		try {
			RoleDTO obj = restTemplate.getForObject(
					this.server + "/find?name=" + name, RoleDTO.class);

			return Optional.of(obj);
			
		} catch (RestClientException e) {			
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}		
	}
	
}

