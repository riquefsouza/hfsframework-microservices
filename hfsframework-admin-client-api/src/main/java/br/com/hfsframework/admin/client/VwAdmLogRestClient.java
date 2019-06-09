package br.com.hfsframework.admin.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.admin.client.domain.VwAdmLog;
import br.com.hfsframework.base.client.BaseRestClient;
import br.com.hfsframework.base.client.IBaseRestClient;

@Service
public class VwAdmLogRestClient extends BaseRestClient<VwAdmLog, Long> implements IBaseRestClient<VwAdmLog, Long> {
	
	//private static final Logger log = LoggerFactory.getLogger(VwAdmLogRestClient.class);
	
	public VwAdmLogRestClient() {
		super();
	}
	
	@Override
	public boolean init(String server, String accesToken) throws RestClientException {
		return super.init(server + "/api/v1/vwAdmLog", accesToken, VwAdmLog.class, 
				new ParameterizedTypeReference<List<VwAdmLog>>() {});
	}
	
}

