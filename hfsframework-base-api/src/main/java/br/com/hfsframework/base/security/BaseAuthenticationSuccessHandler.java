package br.com.hfsframework.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import br.com.hfsframework.util.CookieUtil;

public class BaseAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//public class BaseAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	public static final Logger log = LoggerFactory.getLogger(BaseAuthenticationSuccessHandler.class);
			
	public BaseAuthenticationSuccessHandler() {
		super("/homepage.html");
		setAlwaysUseDefaultTargetUrl(true);
	}
		
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		if (authentication!=null) {
			log.info("LOGIN: " + authentication.getName());
			authentication.getAuthorities().forEach(item -> log.info("AUTHORITIE: " + item.getAuthority()));
			//String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
			//String baseURL = UrlUtil.getURL(request, "/home");
			//response.sendRedirect(baseURL);
			
			Object principal = authentication.getPrincipal();
			
			if (principal instanceof BaseOAuth2RestUser) {
				BaseOAuth2RestUser userLogged = (BaseOAuth2RestUser) principal;
				String sUrlAuthServer = userLogged.getUrlAuthorizationServer();
				String sToken = userLogged.getAccessToken().getValue();
				
				if (CookieUtil.getValue(request, CookieUtil.URL_AUTH_SERVER).isPresent()) {
					CookieUtil.remove(response, CookieUtil.URL_AUTH_SERVER);
				}
				if (CookieUtil.getValue(request, CookieUtil.AUTH_TOKEN).isPresent()) {
					CookieUtil.remove(response, CookieUtil.AUTH_TOKEN);
				}
				
				CookieUtil.createAdd(response, CookieUtil.URL_AUTH_SERVER, sUrlAuthServer, false);
				CookieUtil.createAdd(response, CookieUtil.AUTH_TOKEN, sToken, false);
			}
		}

		super.onAuthenticationSuccess(request, response, authentication);
		
		//response.sendRedirect(request.getContextPath() + "/homepage.html");
	}

}
