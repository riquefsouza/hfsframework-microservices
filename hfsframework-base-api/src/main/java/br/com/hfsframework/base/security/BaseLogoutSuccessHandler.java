package br.com.hfsframework.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class BaseLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	private static final Logger log = LogManager.getLogger(BaseLogoutSuccessHandler.class);
			
	public BaseLogoutSuccessHandler() {
		super();
	}

	@Override
	public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException {
		final String refererUrl = request.getHeader("Referer");
		
		log.info("Referer URL: " + refererUrl);

		super.onLogoutSuccess(request, response, authentication);
	}

}
