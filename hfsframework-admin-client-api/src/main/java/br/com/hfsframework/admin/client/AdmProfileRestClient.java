package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmProfileDTO;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class AdmProfileRestClient extends BaseRestClient<AdmProfileDTO, Long> implements IBaseRestClient<AdmProfileDTO, Long> {
	
	//private static final Logger log = LoggerFactory.getLogger(AdmProfileRestClient.class);
	
	public AdmProfileRestClient() {
		super();
	}
	
	@Override
	public boolean init(String server, String accesToken) throws RestClientException {
		return super.init(server + "/api/v1/admProfile", accesToken, AdmProfileDTO.class, 
				new ParameterizedTypeReference<List<AdmProfileDTO>>() {});
	}
	
}

