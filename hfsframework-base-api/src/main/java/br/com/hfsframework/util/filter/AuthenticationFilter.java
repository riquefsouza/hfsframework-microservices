package br.com.hfsframework.util.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import br.com.hfsframework.util.UrlUtil;

public class AuthenticationFilter implements Filter {

	public static String[] resourceHandler() { 
		//List<String> resources = Arrays.asList("/css/**", "/img/**", "/js/**", "/primeui/**", "/scss/**", "/vendor/**");
		//String[] res = resources.stream().toArray(String[]::new);
		
		String[] res = { "/css/**", "/img/**", "/js/**", "/primeui/**", "/scss/**", "/vendor/**" };
		return res;
	}
	
	public static String[] resourceLocations() {
		//List<String> resources = Arrays.asList("/WEB-INF/static/css/","/WEB-INF/static/img/","/WEB-INF/static/js/",
			//"/WEB-INF/static/primeui/", "/WEB-INF/static/scss/", "/WEB-INF/static/vendor/");
		//String[] res = resources.stream().toArray(String[]::new);
		
		String[] res = { "/WEB-INF/static/css/","/WEB-INF/static/img/","/WEB-INF/static/js/",
				"/WEB-INF/static/primeui/", "/WEB-INF/static/scss/", "/WEB-INF/static/vendor/" };
		
		return res;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String url = httpRequest.getRequestURL().toString();
		String urlLogin = UrlUtil.getURL(httpRequest, "/login.html");
		String urlLoginError = UrlUtil.getURL(httpRequest, "/login-error.html");
		
		
		//HttpUtil.logServletInfo(httpRequest);
		
		//HttpUtil.logClientInfo(httpRequest);

		if (!findResourceUrl(resourceHandler(), url)) {
		
			if (!url.equals(urlLogin) && !url.equals(urlLoginError)) {

				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
				
				if (authenticationTrustResolver.isAnonymous(authentication)) {
					httpResponse.sendRedirect(urlLogin);
				} else {
					chain.doFilter(request, response);
				}
				
			} else {
				chain.doFilter(request, response);
			}	
			
		} else {
			chain.doFilter(request, response);
		}
	}

	private static boolean findResourceUrl(String[] resources, String url) {
		boolean ret = false;
		
		ArrayList<String> resourcesList = new ArrayList<String>(Arrays.asList(resources));
		resourcesList.add("/public/**");
		String[] permitAll = resourcesList.stream().toArray(String[]::new);		
		
		for (String item : permitAll) {
			item = item.replaceAll("[**]", "");

			String pat = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+"+item+"+[-a-zA-Z0-9+&@#/%=~_|]";
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
}
