package br.com.hfsframework.base.client;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.hfsframework.base.view.report.ReportParamsDTO;

public class BaseRestClient<T extends BaseEntityRestClient<T, I>, I extends Serializable> extends BaseRestTemplateClient {

	private static final Logger log = LoggerFactory.getLogger(BaseRestClient.class);
	
	protected ParameterizedTypeReference<List<T>> objList;
	
	protected RestTemplate restTemplate;
	
	protected String server;
	
	private Class<T> classEntity;
	
	public BaseRestClient() {
		super();
		restTemplate = null;
	}

    protected boolean init(String server, String sAccesToken, Class<T> classEntity, 
    		ParameterizedTypeReference<List<T>> objList) throws RestClientException {
    	this.server = server;
    	this.classEntity = classEntity;
    	this.objList = objList;
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

	public List<T> getAll() throws RestClientException {
		try {
			ResponseEntity<List<T>> obj = restTemplate.exchange(
					this.server, HttpMethod.GET, null, objList);
			
			return obj.getBody();
			
		} catch (RestClientException e) {
			log.error(e.getMessage());
			throw new RestClientException(e.getMessage(), e);
		}
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
