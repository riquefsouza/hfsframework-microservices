package br.com.hfsframework.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hfsframework.base.BaseOAuth2RestUser;

@Controller
public class HomeController {
	
	private static final Logger log = LogManager.getLogger(HomeController.class);
			
	//@Value("${server.contextPath}")
	//private String baseURL;

	@RequestMapping(value = "/home")
	public String index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth!=null) {
			log.info("LOGIN: " + auth.getName());
			
			BaseOAuth2RestUser baseUser = (BaseOAuth2RestUser)auth.getPrincipal();
			
			log.info("TOKEN: " + baseUser.getAccessToken().getValue());
			//log.info("LOGIN: " + auth.getPrincipal());
			//log.info("SENHA: " + auth.getCredentials().toString());
			auth.getAuthorities().forEach(item -> log.info("AUTHORITIE: " + item.getAuthority()));
		}
		
		return "index.html";
	}
}
