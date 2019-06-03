package br.com.hfsstore.config;

import javax.servlet.Filter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.hfsframework.util.copyright.CopyrightEnum;
import br.com.hfsframework.util.copyright.CopyrightUtil;

@Order(1)
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private static final Logger log = LogManager.getLogger(AppInitializer.class);
	
    public AppInitializer() {
        super();
    }
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	
    	System.out.print(CopyrightUtil.getAsString(CopyrightEnum.HFSFRAMEWORK_FOR_MICROSERVICES));
    	System.out.print(CopyrightUtil.getAsString(CopyrightEnum.DEVELOPED_BY));
    	
		log.info("------------------------------------------------------------------------");
		log.info("HFSFramework for Microservices");
		log.info("Developed by Henrique Figueiredo de Souza");
		log.info("Version 1.0 - 2019");
		log.info("------------------------------------------------------------------------");
		log.info("Starting HFS Store Web Primeui..."); 
		
		System.out.print(CopyrightUtil.getAsString(CopyrightEnum.HFSSTORE_PRIMEUI_CLIENT_WEB));
				
		//return new Class<?>[] { SecurityConfig.class };
		return null;
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
    	return new Class<?>[] { MvcConfig.class};
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[] { encodingFilter };
    }

}
