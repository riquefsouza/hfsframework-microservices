package br.com.hfsframework.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private static final Logger log = LogManager.getLogger(AppInitializer.class);
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	
		log.info("------------------------------------------------------------------------");
		log.info("HFSFramework for Microservices");
		log.info("Developed by Henrique Figueiredo de Souza");
		log.info("Version 1.0 - 2019");
		log.info("------------------------------------------------------------------------");
		log.info("Starting HFS Framework OAuth Authorization Client Web..."); 
		
    	return null;
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
    	return new Class<?>[] { AppConfig.class};
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
