package br.com.hfsframework.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;

import br.com.hfsframework.base.security.BaseOAuth2RestUser;

public final class AuthenticationUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	//private static final Logger log = LoggerFactory.getLogger(AuthenticationUtil.class);

	public static Optional<BaseOAuth2RestUser> getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null){ 
			Object principal = authentication.getPrincipal();
			
			if (principal instanceof BaseOAuth2RestUser) {
				BaseOAuth2RestUser userLogged = (BaseOAuth2RestUser) principal;
				return Optional.of(userLogged);
			}
		}
		return Optional.empty();
	}
	
	public static boolean checkTokenExpiration(Authentication authentication) {
		if (authentication != null) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof BaseOAuth2RestUser) {
				BaseOAuth2RestUser userLogged = (BaseOAuth2RestUser) principal;
				Date restDate = new Date();
				Date expirationDate = userLogged.getAccessToken().getExpiration();
				// int expirationSeconds = userLogged.getAccessToken().getExpiresIn();
				// Date restDate = DateUtils.addSeconds(new Date(), expirationSeconds);

				return restDate.after(expirationDate);
			}
		}
		return false;
	}

	public static boolean isRedirectView(ModelAndView mv) {

		String viewName = mv.getViewName();
		if (viewName.startsWith("redirect:/")) {
			return true;
		}

		View view = mv.getView();
		return (view != null && view instanceof SmartView && ((SmartView) view).isRedirectView());
	}

	public static boolean isUserLogged() {
		try {
			return !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isUserLoggedAndTokenExpired() {
		if (AuthenticationUtil.isUserLogged()) {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
			
			if (authenticationTrustResolver.isAnonymous(authentication) || AuthenticationUtil.checkTokenExpiration(authentication)) {
				return true;
			}
		}
		return false;
	}
}
