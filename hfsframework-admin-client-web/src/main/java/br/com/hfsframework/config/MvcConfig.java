package br.com.hfsframework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { "br.com.hfsframework" })
public class MvcConfig extends BaseThymeleafMvcConfig {

	public MvcConfig() {
		super();
	}
	
	@Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        //formatterRegistry.addFormatter(new RoleFormatter());
	}
}
