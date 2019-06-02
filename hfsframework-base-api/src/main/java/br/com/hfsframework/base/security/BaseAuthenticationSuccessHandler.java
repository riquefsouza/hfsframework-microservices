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

public class BaseAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//public class BaseAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	public static final Logger log = LoggerFactory.getLogger(BaseAuthenticationSuccessHandler.class);
			
	//private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
	public BaseAuthenticationSuccessHandler() {
		super("/index.html");
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
			
			/*
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
			*/
		}

		super.onAuthenticationSuccess(request, response, authentication);
		
		//handle(request, response, authentication);
        //clearAuthenticationAttributes(request);

	}
	
	/*
	protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (isUser) {
            return "/homepage.html";
        } else if (isAdmin) {
            return "/console.html";
        } else {
            throw new IllegalStateException();
        }
    }

    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }	
*/
}
