package br.com.hfsframework.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.hfsframework.util.UrlUtil;

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
			String baseURL = UrlUtil.getURL(request, "/home");
			response.sendRedirect(baseURL);
		}
	}

}
