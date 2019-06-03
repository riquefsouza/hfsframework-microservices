package br.com.hfsstore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.hfsframework.config.BaseJspMvcConfig;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { "br.com.hfsstore" })
public class MvcConfig extends BaseJspMvcConfig {

	public MvcConfig() {
		super();
	}
	
	@Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        //formatterRegistry.addFormatter(new RoleFormatter());
	}
		
}
