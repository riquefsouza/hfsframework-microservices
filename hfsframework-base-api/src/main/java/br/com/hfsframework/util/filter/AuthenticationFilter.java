package br.com.hfsframework.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

		if (authenticationTrustResolver.isAnonymous(authentication)) {
			redirectToLogin(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	private void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String url = httpRequest.getContextPath() + "/login.html";

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(url);
	}

}
