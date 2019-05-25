package br.com.hfsframework.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CookieUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Logger log = LoggerFactory.getLogger(CookieUtil.class);
	
	public static final String JSESSIONID = "JSESSIONID";
	
	public static final String AUTH_TOKEN = "X-AUTH-TOKEN";

	public static void createAdd(HttpServletResponse response, String name, String value, boolean secure) {
		Cookie cookie = new Cookie(name, value);
		// 1 hour in seconds = 60 seconds x 60 minutes
		// 30 minutes in seconds = 60 seconds x 30 minutes
		cookie.setMaxAge(60 * 30);
		cookie.setHttpOnly(false);
		cookie.setSecure(secure);
		response.addCookie(cookie);
	}

	public static void createAddStrict(HttpServletResponse response, String name, String value, boolean secure) {
		if (secure)
			response.setHeader("Set-Cookie", name + "=" + value +"; Expires=Session; Secure; HttpOnly; SameSite=strict");
		else
			response.setHeader("Set-Cookie", name + "=" + value +"; Expires=Session; HttpOnly; SameSite=strict");
	}
	
	public static void remove(HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	public static Optional<String> getValue(HttpServletRequest request, String name) {
		if (request.getCookies()!=null) {
			return Arrays.stream(request.getCookies())
				      .filter(c -> name.equals(c.getName()))
				      .map(Cookie::getValue)
				      .findAny();
		}
		return Optional.empty();
	}
	
	/*
	public static String getValue(HttpServletRequest request, String name) {
		String value = "";
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie item : cookies) {
				if (item.getName().equals(name)) {
					value = item.getValue();
					break;
				}
			}
		}
		return value;
	}
	*/

	public static void listAll(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie item : cookies) {
				log.info("COOKIE [HttpOnly: " + item.isHttpOnly() + ", Secure: " + item.getSecure() 
				+ "] --> " + item.getName() + ": " + item.getValue());
			}
		}
	}

}
