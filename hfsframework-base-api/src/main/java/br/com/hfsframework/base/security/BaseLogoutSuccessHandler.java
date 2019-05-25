package br.com.hfsframework.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class BaseLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	public static final Logger log = LoggerFactory.getLogger(BaseLogoutSuccessHandler.class);
			
	public BaseLogoutSuccessHandler() {
		super();
	}

	@Override
	public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException {
		final String refererUrl = request.getHeader("Referer");
		
		log.info("Referer URL: " + refererUrl);
		
		/*
		if (CookieUtil.getValue(request, CookieUtil.URL_AUTH_SERVER).isPresent()) {
			CookieUtil.remove(response, CookieUtil.URL_AUTH_SERVER);
		}
		if (CookieUtil.getValue(request, CookieUtil.AUTH_TOKEN).isPresent()) {
			CookieUtil.remove(response, CookieUtil.AUTH_TOKEN);
		}
		*/

		super.onLogoutSuccess(request, response, authentication);
	}

}
