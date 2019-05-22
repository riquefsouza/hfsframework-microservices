package br.com.hfsframework.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = {"br.com.hfsframework"} )  
public class MvcConfig implements WebMvcConfigurer {

	/*
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    */
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add( new PageableHandlerMethodArgumentResolver());
    }
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(createMappingJackson2HttpMessageConverter());
        converters.add(createXmlHttpMessageConverter());
    }

    @Bean
    public HttpMessageConverter<Object> createMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        return converter;
    }
    
    @Bean
    public HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MarshallingHttpMessageConverter xmlConverter = 
          new MarshallingHttpMessageConverter();
 
        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);
 
        return xmlConverter;
    }

}
