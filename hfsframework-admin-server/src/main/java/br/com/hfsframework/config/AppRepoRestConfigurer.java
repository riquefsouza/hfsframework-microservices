package br.com.hfsframework.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class AppRepoRestConfigurer implements RepositoryRestConfigurer {

	/*
		Name				Description
		basePath			root URI for Spring Data REST
		defaultPageSize		change default number of items served in a single page
		maxPageSize			change maximum number of items in a single page
		pageParamName		change name of the query parameter for selecting pages
		limitParamName		change name of the query parameter for number of items to show in a page
		sortParamName		change name of the query parameter for sorting
		defaultMediaType	change default media type to use when none is specified
		returnBodyOnCreate	change if a body should be returned on creating a new entity
		returnBodyOnUpdate	change if a body should be returned on updating an entity
	 */
	
	@Autowired
	private EntityManager em;	
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		//config.setBasePath("/api");
		//config.setDefaultPageSize(defaultPageSize);
		
		List<Class<?>> listJavaType = em.getMetamodel().getEntities().stream().map(e -> e.getJavaType())
		.collect(Collectors.toList());
		
		Class<?>[] domainTypes = listJavaType.toArray(new Class[0]);
		
		config.exposeIdsFor(domainTypes);		
	}

}
