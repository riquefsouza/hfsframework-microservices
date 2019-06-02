package br.com.hfsframework.base.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.util.AuthenticationUtil;

public class BaseUserInterceptor extends HandlerInterceptorAdapter {

	//private static Logger log = LoggerFactory.getLogger(BaseUserInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		if (AuthenticationUtil.isUserLogged()) {
			addToModelUserDetails(request.getSession());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		if (model != null && !AuthenticationUtil.isRedirectView(model)) {
			if (AuthenticationUtil.isUserLogged()) {
				addToModelUserDetails(model);
			}
		}
	}

	private void addToModelUserDetails(HttpSession session) {
		String authServerURL = (String)session.getAttribute("authServerURL"); 
		
		if (authServerURL==null) {
			Optional<BaseOAuth2RestUser> userLogged = AuthenticationUtil.getPrincipal();
			if (userLogged.isPresent()) {		
				
				authServerURL = userLogged.get().getUrlAuthorizationServer();
				String accesToken = userLogged.get().getAccessToken().getValue();
				
				session.setAttribute("urlAuthServer", authServerURL);
				session.setAttribute("authToken", accesToken); 
				session.setAttribute("userLogged", userLogged.get());
			}			
		}
		
	}

	private void addToModelUserDetails(ModelAndView model) {
		if (!model.getModelMap().containsAttribute("urlAuthServer")) {
			Optional<BaseOAuth2RestUser> userLogged = AuthenticationUtil.getPrincipal();
			if (userLogged.isPresent()) {		
				String authServerURL = userLogged.get().getUrlAuthorizationServer();
				String accesToken = userLogged.get().getAccessToken().getValue();
								
				model.addObject("urlAuthServer", authServerURL);
				model.addObject("authToken", accesToken);
				model.addObject("userLogged", userLogged.get());
			}			
		}

	}


}