package br.com.hfsframework.admin.client;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;
import br.com.hfsframework.admin.client.domain.AdmMenu;

@Service
public class AdmMenuRestClient extends BaseRestClient<AdmMenu, Long> implements IBaseRestClient<AdmMenu, Long> {
	
	private static final Logger log = LoggerFactory.getLogger(AdmMenuRestClient.class);
	
	public AdmMenuRestClient() {
		super();
	}
	
	@Override
	public boolean init(String authServerURL, String accesToken) throws RestClientException {
		return super.init(authServerURL + "/api/v1/admMenu", accesToken, AdmMenu.class, 
				new ParameterizedTypeReference<List<AdmMenu>>() {});
	}
	
}

