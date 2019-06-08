package br.com.hfsframework.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;

import br.com.hfsframework.base.BaseErrorDescription;
import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.base.security.DecodedJwt;

public final class AuthenticationUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(AuthenticationUtil.class);

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
				//Date restDate = new Date();
				//Date expirationDate = userLogged.getAccessToken().getExpiration();
				
				// int expirationSeconds = userLogged.getAccessToken().getExpiresIn();
				// Date restDate = DateUtils.addSeconds(new Date(), expirationSeconds);

				//return restDate.after(expirationDate);
				return userLogged.getAccessToken().isExpired();
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
	
	private static Optional<DecodedJwt> getToken(HttpServletRequest request) {
		String sToken = request.getHeader("Authorization");
		String sContentType = request.getHeader("Content-Type");
		
		if (sToken!=null && sContentType!=null) {
			if (sContentType.contains("application/json")) {
				if (sToken.startsWith("Bearer")){
					sToken = sToken.replaceFirst("Bearer", "").trim();
				}
						
				DecodedJwt decodedJwt = DecodedJwt.getDecodedJwt(sToken);
				return Optional.of(decodedJwt);
			}
		}
		return Optional.empty();
	}
	
	public static boolean isTokenAuthorized(HttpServletRequest request, String authClientId) {
		Optional<DecodedJwt> token = getToken(request);
		
		if (token.isPresent()) {
			if (token.get().getClientId().equals(authClientId) && token.get().isValid()
					&& token.get().isAuthenticated() && !token.get().isExpired()) {
				return true;
			}
		}		
		return false;
	}

	public static boolean isTokenExpired(HttpServletRequest request, String authClientId) {
		Optional<DecodedJwt> token = getToken(request);
		
		if (token.isPresent()) {
			if (token.get().getClientId().equals(authClientId) && token.get().isValid()
					&& token.get().isExpired()) {
				return true;
			}
		}		
		return false;
	}

	public static boolean tokenHasRole(HttpServletRequest request, String role) {
		Optional<DecodedJwt> token = getToken(request);
		
		if (token.isPresent()) {
			if (token.get().isValid()) {
				return token.get().getAuthorities().contains("ROLE_" + role);
			}
		}
		return false;
	}
	
	public static boolean tokenHasScope(HttpServletRequest request, String scope) {
		Optional<DecodedJwt> token = getToken(request);
		
		if (token.isPresent()) {
			if (token.get().isValid()) {
				return token.get().getScope().contains(scope);
			}
		}
		return false;
	}	
	
	public static boolean isTokenExpiredOrUnauthorized(HttpServletRequest request, 
			HttpServletResponse response, String authClientId) {
		if (AuthenticationUtil.isTokenExpired(request, authClientId)) {
			try {
				String sToken = request.getHeader("Authorization");
				response.getWriter().println(BaseErrorDescription.buildInvalidToken(sToken));
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			return true;
		}

		if (!AuthenticationUtil.isTokenAuthorized(request, authClientId)) {
			return true;
		}
		
		return false;
	}	
}
