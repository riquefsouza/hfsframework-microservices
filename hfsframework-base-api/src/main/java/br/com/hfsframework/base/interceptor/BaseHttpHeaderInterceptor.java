package br.com.hfsframework.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseHttpHeaderInterceptor extends HandlerInterceptorAdapter {

	//private static Logger log = LoggerFactory.getLogger(BaseHttpHeaderInterceptor.class);
	
	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		//String url = request.getRequestURL().toString();
		
		//log.info(url);
		
		/*
		
		if (!ResourceUtil.findResourceUrl(ResourceUtil.resourceHandler(), url)) {

			String sAuthorization = request.getHeader("Authorization");
			String sContentType = request.getHeader("Content-Type");		

			if (sContentType.contains("application/json;charset=UTF-8")) {
				log.info("Authorization: " + sAuthorization);
			}
		}
		*/
		
		return true;
	}
	
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		//log.info("Authorization: " + sAuthorization);
		//log.info("Content-Type: " + sContentType);
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		//if (ex != null)
			//ex.printStackTrace();
		
	}
}
