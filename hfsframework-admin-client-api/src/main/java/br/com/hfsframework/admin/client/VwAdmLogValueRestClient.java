package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.VwAdmLogValue;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class VwAdmLogValueRestClient extends BaseRestClient<VwAdmLogValue, Long> implements IBaseRestClient<VwAdmLogValue, Long> {
	
	//private static final Logger log = LoggerFactory.getLogger(VwAdmLogValueRestClient.class);
	
	public VwAdmLogValueRestClient() {
		super();
	}
	
	@Override
	public boolean init(String server, String accesToken) throws RestClientException {
		return super.init(server + "/api/v1/vwAdmLogValue", accesToken, VwAdmLogValue.class, 
				new ParameterizedTypeReference<List<VwAdmLogValue>>() {});
	}
	
}

