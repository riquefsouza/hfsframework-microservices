package br.com.hfsframework.config;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { "br.com.hfsframework" })
public class MvcConfig implements WebMvcConfigurer, ApplicationContextAware {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/*
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    */
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.US);
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**", "/img/**", "/js/**", "/primeui/**", "/scss/**", "/vendor/**")
				.addResourceLocations("/WEB-INF/static/css/", 
						"/WEB-INF/static/img/", "/WEB-INF/static/js/",
						"/WEB-INF/static/primeui/", "/WEB-INF/static/scss/", 
						"/WEB-INF/static/vendor/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
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
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		return xmlConverter;
	}

	@Bean
	@Description("Thymeleaf template resolver serving HTML")
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCacheable(false);
		return resolver;
	}

	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		return engine;
	}

	@Bean
	@Description("Thymeleaf view resolver")
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/anonymous.html");
		registry.addViewController("/login.html");
		registry.addViewController("/homepage.html");
		registry.addViewController("/accessDenied");

		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
