package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.AdmMenuDTO;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class AdmMenuRestClient extends BaseRestClient<AdmMenuDTO, Long> implements IBaseRestClient<AdmMenuDTO, Long> {
	
	//private static final Logger log = LoggerFactory.getLogger(AdmMenuRestClient.class);
	
	public AdmMenuRestClient() {
		super();
	}
	
	@Override
	public boolean init(String server, String accesToken) throws RestClientException {
		return super.init(server + "/api/v1/admMenu", accesToken, AdmMenuDTO.class, 
				new ParameterizedTypeReference<List<AdmMenuDTO>>() {});
	}
	
}

