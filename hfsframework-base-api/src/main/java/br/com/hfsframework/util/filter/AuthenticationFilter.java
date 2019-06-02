package br.com.hfsframework.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.hfsframework.util.AuthenticationUtil;
import br.com.hfsframework.util.HttpUtil;
import br.com.hfsframework.util.ResourceUtil;
import br.com.hfsframework.util.UrlUtil;

public class AuthenticationFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String url = httpRequest.getRequestURL().toString();
		String urlLogin = UrlUtil.getURL(httpRequest, "/login.html");
		String urlLoginError = UrlUtil.getURL(httpRequest, "/login-error.html");
		String urlExpired = UrlUtil.getURL(httpRequest, "/sessionExpired.html");
		
		if (log.isDebugEnabled()) {
			HttpUtil.logServletInfo(httpRequest);		
			HttpUtil.logClientInfo(httpRequest);
		}
		
		if (!ResourceUtil.findResourceUrl(ResourceUtil.resourceHandler(), url)) {
		
			if (!url.equals(urlLogin) && !url.equals(urlLoginError)) {

				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
				
				if (authenticationTrustResolver.isAnonymous(authentication)) {
					httpResponse.sendRedirect(urlLogin);
				} else {
					
					if (AuthenticationUtil.isUserLoggedAndTokenExpired() && !url.equals(urlExpired))
						httpResponse.sendRedirect(urlExpired);
					else
						chain.doFilter(request, response);
				}
				
			} else {
				chain.doFilter(request, response);
			}	
			
		} else {
			chain.doFilter(request, response);
		}
	}


	
}
