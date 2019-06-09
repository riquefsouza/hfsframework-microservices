package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmUser;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class AdmUserRestClient extends BaseRestClient<AdmUser, Long> implements IBaseRestClient<AdmUser, Long> {
	
	//private static final Logger log = LoggerFactory.getLogger(AdmUserRestClient.class);
	
	public AdmUserRestClient() {
		super();
	}
	
	@Override
	public boolean init(String server, String accesToken) throws RestClientException {
		return super.init(server + "/api/v1/admUser", accesToken, AdmUser.class, 
				new ParameterizedTypeReference<List<AdmUser>>() {});
	}
	
}

