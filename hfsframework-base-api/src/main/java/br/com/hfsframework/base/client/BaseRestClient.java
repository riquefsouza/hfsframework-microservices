package br.com.hfsframework.base.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.hfsframework.base.view.report.ReportParamsDTO;

public class BaseRestClient<T extends BaseEntityRestClient<I>, I extends Serializable> extends BaseRestTemplateClient implements IBaseRestClient<T, I> {

	private static final Logger log = LoggerFactory.getLogger(BaseRestClient.class);
	
	protected List<T> objList = new ArrayList<T>();
	
	protected RestTemplate restTemplate;
	
	protected String server;
	
	private Class<T> classEntity;
	
	public BaseRestClient() {
		super();
		restTemplate = null;
	}
	
    public boolean init(String server, String sAccesToken, Class<T> classEntity) throws RestClientException {
    	this.server = server;
    	this.classEntity = classEntity;
    	
        restTemplate = restTemplate(sAccesToken);
        
        return (restTemplate!=null);
	}
	
	public Optional<T> add(T bean) throws RestClientException {
		try {
			T saved = restTemplate.postForObject(
					this.server, bean, classEntity);
			
			return Optional.of(saved);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
		//return Optional.empty();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() throws RestClientException {
		List<T> obj = new ArrayList<T>();
		try {
			obj = restTemplate.getForObject(
					this.server, objList.getClass());
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
		return obj;
	}

	public Optional<T> getById(I id) throws RestClientException {
		try {
			T obj = restTemplate.getForObject(
					this.server + "/{id}", classEntity, id);
			
			return Optional.of(obj);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}		
		//return Optional.empty();
	}

	public Optional<T> updateById(T bean) throws RestClientException {
		try {
			restTemplate.put(this.server + "/{id}", bean, bean.getId());
			
			return Optional.of(bean);
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
		//return Optional.empty();
	}
	
	public boolean deleteById(I id) throws RestClientException {
		try {
			restTemplate.delete(this.server + "/{id}", id);			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
			//return false;
		}
		return true;
	}
	
	public Optional<byte[]> report(ReportParamsDTO reportParamsDTO) throws RestClientException {
		try {
			ResponseEntity<ByteArrayResource> obj = restTemplate.postForEntity(
					this.server + "/report", reportParamsDTO, ByteArrayResource.class);
			
			return Optional.of(obj.getBody().getByteArray());
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
		
		//return Optional.empty();
	}
	
}
