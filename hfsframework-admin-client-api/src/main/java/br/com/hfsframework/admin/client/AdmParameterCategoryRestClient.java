package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmParameterCategory;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class AdmParameterCategoryRestClient extends BaseRestClient<AdmParameterCategory, Long>
		implements IBaseRestClient<AdmParameterCategory, Long> {

	public AdmParameterCategoryRestClient() {
		super();
	}

	@Override
	public boolean init(String authServerURL, String accesToken) throws RestClientException {
		return super.init(authServerURL + "/api/v1/AdmParameterCategory", 
				accesToken, AdmParameterCategory.class,
				new ParameterizedTypeReference<List<AdmParameterCategory>>() {
				});
	}

}
