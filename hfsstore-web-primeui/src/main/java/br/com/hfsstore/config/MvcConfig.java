package br.com.hfsstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public MvcConfig() {
		super();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**", "/img/**", "/js/**", "/primeui/**", "/scss/**", "/vendor/**")
                .addResourceLocations(
                		"/WEB-INF/static/css/",
                		"/WEB-INF/static/img/",                        
                        "/WEB-INF/static/js/",
                		"/WEB-INF/static/primeui/",
                		"/WEB-INF/static/scss/",                        
                        "/WEB-INF/static/vendor/");		
	}
	
	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {

		registry.addViewController("/anonymous.html");
		registry.addViewController("/login.html");
		registry.addViewController("/homepage.html");
		registry.addViewController("/admin/adminpage.html");
		registry.addViewController("/accessDenied");
	}

	@Bean
	public ViewResolver viewResolver() {
		final InternalResourceViewResolver bean = new InternalResourceViewResolver();

		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");

		return bean;
	}
}
