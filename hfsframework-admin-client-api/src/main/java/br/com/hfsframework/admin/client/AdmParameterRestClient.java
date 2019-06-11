package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmParameterDTO;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class AdmParameterRestClient extends BaseRestClient<AdmParameterDTO, Long>
		implements IBaseRestClient<AdmParameterDTO, Long> {

	public AdmParameterRestClient() {
		super();
	}
	
	@Override
	public boolean init(String authServerURL, String accesToken) throws RestClientException {
		return super.init(authServerURL + "/api/v1/AdmParameter", accesToken, AdmParameterDTO.class, 
				new ParameterizedTypeReference<List<AdmParameterDTO>>() {});
	}	

}
