package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmPage;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class AdmPageRestClient extends BaseRestClient<AdmPage, Long> implements IBaseRestClient<AdmPage, Long> {
	
	//private static final Logger log = LoggerFactory.getLogger(AdmPageRestClient.class);
	
	public AdmPageRestClient() {
		super();
	}
	
	@Override
	public boolean init(String server, String accesToken) throws RestClientException {
		return super.init(server + "/api/v1/admPage", accesToken, AdmPage.class, 
				new ParameterizedTypeReference<List<AdmPage>>() {});
	}
	
}

