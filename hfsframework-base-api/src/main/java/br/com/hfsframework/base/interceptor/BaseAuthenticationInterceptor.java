package br.com.hfsframework.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.hfsframework.util.AuthenticationUtil;
import br.com.hfsframework.util.network.UrlUtil;

public class BaseAuthenticationInterceptor extends HandlerInterceptorAdapter {

	// private static Logger log =
	// LoggerFactory.getLogger(BaseAuthenticationInterceptor.class);

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {

		if (AuthenticationUtil.isUserLoggedAndTokenExpired()) {
			
			String urlExpired = UrlUtil.getURL(request, "/logout");
			//String urlExpired = UrlUtil.getURL(request, "/sessionExpired.html");			
			
			SecurityContextHolder.clearContext();
			request.logout();
			response.sendRedirect(urlExpired);
		}

		return true;
	}

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		/*
		if (modelAndView != null && !AuthenticationUtil.isRedirectView(modelAndView)) {
			if (AuthenticationUtil.isUserLoggedAndTokenExpired()) {
				modelAndView.addObject("dangerMessage", "TOKEN EXPIROU!");
			}
		}
		 */
	}

}
