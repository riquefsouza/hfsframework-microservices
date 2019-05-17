package br.com.hfsframework.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class BaseAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger log = LogManager.getLogger(BaseAuthenticationSuccessHandler.class);
			
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (authentication!=null) {
			log.info("LOGIN: " + authentication.getName());
			//log.info("SENHA: " + authentication.getCredentials().toString());
			authentication.getAuthorities().forEach(item -> log.info("AUTHORITIE: " + item.getAuthority()));
			//String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
			String baseURL = getURL(request, "/home");
			response.sendRedirect(baseURL);
		}
	}

	public String getURL(HttpServletRequest req, String servletPath) {

	    String scheme = req.getScheme();          
	    String serverName = req.getServerName();  
	    int serverPort = req.getServerPort();     
	    String contextPath = req.getContextPath();
	    //String servletPath = req.getServletPath();
	    //String pathInfo = req.getPathInfo();      
	    //String queryString = req.getQueryString();

	    // Reconstruct original requesting URL
	    StringBuilder url = new StringBuilder();
	    url.append(scheme).append("://").append(serverName);

	    if (serverPort != 80 && serverPort != 443) {
	        url.append(":").append(serverPort);
	    }

	    url.append(contextPath).append(servletPath);

	    /*
	    if (pathInfo != null) {
	        url.append(pathInfo);
	    }
	    if (queryString != null) {
	        url.append("?").append(queryString);
	    }
	    */
	    
	    return url.toString();
	}	
}
