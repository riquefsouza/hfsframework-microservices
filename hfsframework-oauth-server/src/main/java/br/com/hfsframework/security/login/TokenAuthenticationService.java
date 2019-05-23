package br.com.hfsframework.security.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

import br.com.hfsframework.util.CookieUtil;
import io.jsonwebtoken.MalformedJwtException;

public class TokenAuthenticationService {	

	private final TokenHandler tokenHandler;

	public TokenAuthenticationService(String secret, UserDetailsService userDetailsService) {
		this.tokenHandler = new TokenHandler(secret, userDetailsService);
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final UserDetails user = authentication.getDetails();
		response.addHeader(CookieUtil.AUTH_TOKEN, tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(CookieUtil.AUTH_TOKEN);
		if (!StringUtils.hasText(token)) {
			token = request.getParameter(CookieUtil.AUTH_TOKEN);
		}
		if (token != null) {
			try {
				final UserDetails user = tokenHandler.parseUserFromToken(token);
				if (user != null) {
					return new UserAuthentication(user);
				}
			} catch (MalformedJwtException exception) {		
				exception.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
