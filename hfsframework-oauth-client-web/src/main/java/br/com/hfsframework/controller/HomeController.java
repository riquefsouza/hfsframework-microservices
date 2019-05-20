package br.com.hfsframework.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private static final Logger log = LogManager.getLogger(HomeController.class);
			
	//@Value("${server.contextPath}")
	//private String baseURL;

	@RequestMapping(value = "/home")
	public String index(HttpServletRequest request, Principal principal, Authentication authentication) {
		Principal principalRequest = request.getUserPrincipal();
		
		if (principalRequest!=null) {
			log.info("REQUEST LOGIN: " + principalRequest.getName());
		}
		if (principal!=null) {
			log.info("PRINCIPAL LOGIN: " + principal.getName());
		}
		if (authentication!=null) {
			log.info("AUTHENTICATION LOGIN: " + authentication.getName());
			
			if (authentication.getPrincipal()!=null) {
				//BaseOAuth2RestUser userDetails = (BaseOAuth2RestUser) authentication.getPrincipal();
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				if (userDetails.getAuthorities()!=null) {
					log.info("AUTHENTICATION User has authorities: " + userDetails.getAuthorities());
				}
			}
		}
		
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth!=null) {
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				log.info("SECURITY LOGIN: " + auth.getName());
				
				//BaseOAuth2RestUser baseUser = (BaseOAuth2RestUser)auth.getPrincipal();
				//UserDetails baseUser = (UserDetails) authentication.getPrincipal();
				
				//log.info("SECURITY TOKEN: " + baseUser.getAccessToken().getValue());
				log.info("SECURITY PRINCIPAL: " + auth.getPrincipal());
				//log.info("SECURITY SENHA: " + auth.getCredentials().toString());
				auth.getAuthorities().forEach(item -> log.info("SECURITY AUTHORITIE: " + item.getAuthority()));
				log.info("SECURITY isAuthenticated: " + auth.isAuthenticated());
			}
		}
		
		return "index.html";
	}
}
