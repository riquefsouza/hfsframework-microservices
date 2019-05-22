package br.com.hfsframework.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class XFrameHeaderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Server", "Tomcat");
		httpResponse.setHeader("X-Powered-By", "Apache");
		httpResponse.setHeader("X-XSS-Protection", "1; mode=block");
		httpResponse.setHeader("X-Content-Type-Options", "nosniff");
		httpResponse.setHeader("CACHE-CONTROL", "NO-CACHE");
		httpResponse.setHeader("PRAGMA", "NO-CACHE");
		httpResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
		httpResponse.setHeader("strict-transport-security", "max-age=604800");

		chain.doFilter(request, response);
	}

}
