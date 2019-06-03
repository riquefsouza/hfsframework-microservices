package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmParameter;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class AdmParameterRestClient extends BaseRestClient<AdmParameter, Long>
		implements IBaseRestClient<AdmParameter, Long> {

	public AdmParameterRestClient() {
		super();
	}
	
	@Override
	public boolean init(String authServerURL, String accesToken) throws RestClientException {
		return super.init(authServerURL + "/api/v1/AdmParameter", accesToken, AdmParameter.class, 
				new ParameterizedTypeReference<List<AdmParameter>>() {});
	}	

}
