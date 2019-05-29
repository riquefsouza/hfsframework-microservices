package br.com.hfsframework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HttpUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

	public static void logServletInfo(HttpServletRequest request) {
		List<String> listInfo = servletInfo(request);
		listInfo.forEach(info -> log.info(info));
	}

	public static List<String> servletInfo(HttpServletRequest request) {
		List<String> list = new ArrayList<String>();

		list.add("[SERVLET INFO]");
		list.add("--------------");
		list.add("[PATHS]");
		list.add("Request URL : " + request.getRequestURL());
		list.add("Request URI : " + request.getRequestURI());
		list.add("Servlet path : " + request.getServletPath());
		list.add("--------------");
		list.add("[HEADERS]");
		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String param = (String) e.nextElement();
			list.add(param + " : " + request.getHeader(param));
		}
		list.add("--------------");
		list.add("[PARAMETERS]");
		Map<String, String[]> paramsMap = request.getParameterMap();
		for (String key : paramsMap.keySet()) {
			list.add(key + " : " + request.getParameter(key));
		}
		list.add("--------------");
		list.add("[SESSION]");
		list.add("Client IP address : " + request.getRemoteAddr());
		list.add("Session ID : " + request.getRequestedSessionId());
		list.add("--------------");
		list.add("[COOKIES]");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				list.add(cookie.getName() + ";");
			}
		}
		list.add("--------------");
		return list;
	}

	public static void logClientInfo(HttpServletRequest request) {
		List<String> listInfo = clientInfo(request);
		listInfo.forEach(info -> log.info(info));		
	}
	
	public static List<String> clientInfo(HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
		
		final String referer = getReferer(request);
		final String fullURL = getFullURL(request);
		final String clientIpAddr = getClientIpAddr(request);
		final String clientOS = getClientOS(request);
		final String clientBrowser = getClientBrowser(request);
		final String userAgent = getUserAgent(request);

		list.add("[CLIENT INFO]");
		list.add("--------------");
		list.add("User Agent: " + userAgent);
		list.add("User Agent: " + userAgent);
		list.add("Operating System: " + clientOS);
		list.add("Browser Name: " + clientBrowser);
		list.add("IP Address: " + clientIpAddr);
		list.add("Full URL: " + fullURL);
		list.add("Referrer: " + referer);
		list.add("--------------");
         return list;
	}

	private static String getReferer(HttpServletRequest request) {
		final String referer = request.getHeader("referer");
		return referer;
	}

	private static String getFullURL(HttpServletRequest request) {
		final StringBuffer requestURL = request.getRequestURL();
		final String queryString = request.getQueryString();

		final String result = queryString == null ? requestURL.toString()
				: requestURL.append('?').append(queryString).toString();

		return result;
	}

	private static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private static String getClientOS(HttpServletRequest request) {
		final String browserDetails = request.getHeader("User-Agent");

		final String lowerCaseBrowser = browserDetails.toLowerCase();
		if (lowerCaseBrowser.contains("windows")) {
			return "Windows";
		} else if (lowerCaseBrowser.contains("mac")) {
			return "Mac";
		} else if (lowerCaseBrowser.contains("x11")) {
			return "Unix";
		} else if (lowerCaseBrowser.contains("android")) {
			return "Android";
		} else if (lowerCaseBrowser.contains("iphone")) {
			return "IPhone";
		} else {
			return "UnKnown, More-Info: " + browserDetails;
		}
	}

	private static String getClientBrowser(HttpServletRequest request) {
		final String browserDetails = request.getHeader("User-Agent");
		final String user = browserDetails.toLowerCase();

		String browser = "";

		if (user.contains("msie")) {
			String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/", "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			browser = "IE";
		} else {
			browser = "UnKnown, More-Info: " + browserDetails;
		}

		return browser;
	}

	private static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
}
