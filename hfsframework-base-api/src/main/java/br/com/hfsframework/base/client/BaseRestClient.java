package br.com.hfsframework.base.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.hfsframework.base.view.report.ReportParamsDTO;

public class BaseRestClient<T extends BaseEntityRestClient<I>, I> extends BaseRestTemplateClient {

	private static final Logger log = LogManager.getLogger(BaseRestClient.class);
	
	protected List<T> objList = new ArrayList<T>();
	
	private RestTemplate restTemplate = null;
	
	protected String server;
	
	private Class<T> classEntity;
	
    public BaseRestClient(String server, String sAccesToken, Class<T> classEntity) {
    	this.server = server;
    	this.classEntity = classEntity;
    	
        restTemplate = restTemplate(sAccesToken);
	}
	
	public Optional<T> add(T bean) {
		try {
			T saved = restTemplate.postForObject(
					this.server, bean, classEntity);
			
			return Optional.of(saved);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
		return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		List<T> obj = new ArrayList<T>();
		try {
			obj = restTemplate.getForObject(
					this.server, objList.getClass());
		} catch (RestClientException e) {
			log.error(e.getMessage());			
		}
		return obj;
	}

	public Optional<T> getById(I id) {
		try {
			T obj = restTemplate.getForObject(
					this.server + "/{id}", classEntity, id);
			
			return Optional.of(obj);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
		
		return Optional.empty();
	}

	public Optional<T> updateById(T bean) {
		try {
			restTemplate.put(this.server + "/{id}", bean, bean.getId());
			
			return Optional.of(bean);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
		return Optional.empty();
	}
	
	public boolean deleteById(I id) {
		try {
			restTemplate.delete(this.server + "/{id}", id);			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public Optional<byte[]> report(ReportParamsDTO reportParamsDTO) {
		try {
			ResponseEntity<ByteArrayResource> obj = restTemplate.postForEntity(
					this.server + "/report", reportParamsDTO, ByteArrayResource.class);
			
			return Optional.of(obj.getBody().getByteArray());
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
		}
		
		return Optional.empty();
	}
	
}
