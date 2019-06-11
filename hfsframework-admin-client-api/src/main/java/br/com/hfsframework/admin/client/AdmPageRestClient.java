package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmPageDTO;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class AdmPageRestClient extends BaseRestClient<AdmPageDTO, Long> implements IBaseRestClient<AdmPageDTO, Long> {
	
	//private static final Logger log = LoggerFactory.getLogger(AdmPageRestClient.class);
	
	public AdmPageRestClient() {
		super();
	}
	
	@Override
	public boolean init(String server, String accesToken) throws RestClientException {
		return super.init(server + "/api/v1/admPage", accesToken, AdmPageDTO.class, 
				new ParameterizedTypeReference<List<AdmPageDTO>>() {});
	}
	
}

