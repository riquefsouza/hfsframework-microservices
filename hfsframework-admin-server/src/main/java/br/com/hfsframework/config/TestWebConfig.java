package br.com.hfsframework.config;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.hfsframework.util.network.HttpMessageConverterUtil;

@Configuration
@ComponentScan(basePackages = {"br.com.hfsframework"})
public class TestWebConfig implements WebMvcConfigurer, ApplicationContextAware {

    @SuppressWarnings("unused")
	private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.addAll(HttpMessageConverterUtil.getMessageConverters());
	}
    
}
