package br.com.hfsframework.base.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.client.BaseOAuth2RestTemplateClient;

public class BaseRestClientTest<T extends BaseEntityRestClientTest<I>, I> extends BaseOAuth2RestTemplateClient {

	private static final Logger log = LogManager.getLogger(BaseRestClientTest.class);
	
	protected List<T> objList = new ArrayList<T>();
	
	private OAuth2RestTemplate oauth2RestTemplate = null;
	
	protected String server;
	
	private Class<T> classEntity;
	
    public BaseRestClientTest(String oauthServer, String server, Class<T> classEntity, String login, String password) {
    	this.server = server;
    	this.classEntity = classEntity;
    	
        oauth2RestTemplate = restTemplate(oauthServer, login, password);
	}
	
	public void addAll() {
    	log.info(METHOD_ACTION.ADD_ALL);
    	
    	this.objList.stream().forEach(bean -> {
    		try {
    			
    			T saved = oauth2RestTemplate.postForObject(
    					this.server, bean, classEntity);
    			
    			bean.setId(saved.getId());
    			
    			log.info("added: " + saved);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}

	@SuppressWarnings("unchecked")
	public void getAll() {
		log.info(METHOD_ACTION.GET_ALL);
		
		try {
			List<T> obj = oauth2RestTemplate.getForObject(
					this.server, objList.getClass());
			Arrays.asList(obj).stream().forEach(bean -> log.info("getAll: " + bean));
		} catch (RestClientException e) {
			log.error(e.getMessage());			
		}
	}

	public void getById() {
		log.info(METHOD_ACTION.GET_BY_ID);
    	this.objList.stream().forEach(bean -> {
    		
			try {
				T obj = oauth2RestTemplate.getForObject(
						this.server + "/{id}", classEntity, bean.getId());
				
				log.info("getById: " + obj);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}

	public void updateById() {
		log.info(METHOD_ACTION.UPDATE_BY_ID);

		this.objList.stream().forEach(bean -> bean.setDescription("DESC "+bean.getId()));
    	
    	this.objList.stream().forEach(bean -> {
			try {
				oauth2RestTemplate.put(this.server + "/{id}", bean, bean.getId());
    			
				log.info("updated: " + bean);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}
	
	public void getEntity() {
		this.objList.stream().forEach(bean -> {
			try {
				ResponseEntity<T> entity = oauth2RestTemplate.getForEntity(this.server + "/{id}", 
						classEntity, bean.getId());
				T obj = (T) entity.getBody();
				log.info("getEntity: " + obj);
		
				HttpHeaders responseHeaders = entity.getHeaders();
				responseHeaders.entrySet().forEach(header-> {
					log.info(header.getKey() + ":" + header.getValue());
				});
		
				log.info("status name: " + entity.getStatusCode().name());
				log.info("status value: " + entity.getStatusCode().value());
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}
	
	public void deleteById() {
		log.info(METHOD_ACTION.DELETE_BY_ID);
		
    	this.objList.stream().forEach(bean -> {
			try {
				oauth2RestTemplate.delete(this.server + "/{id}", bean.getId());
    			
				log.info("deleted: " + bean);
    		} catch (RestClientException e) {
    			log.error(e.getMessage());
    		}			
    	});				
	}	
}
