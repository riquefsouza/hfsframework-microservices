package br.com.hfsframework.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class BaseAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger log = LogManager.getLogger(BaseAccessDeniedHandler.class);
			
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
			throws IOException, ServletException {
		
		log.error("Access-Denied: " + ex.getMessage());
		
		response.sendRedirect("/paginaErro");
		
	}
}