package br.com.hfsframework.util.network;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriUtils;

public final class UrlUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(UrlUtil.class);

	public static String getURL(HttpServletRequest req, String servletPath) {

		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();

		StringBuilder url = new StringBuilder();
		url.append(scheme).append("://").append(serverName);

		if (serverPort != 80 && serverPort != 443) {
			url.append(":").append(serverPort);
		}

		url.append(contextPath).append(servletPath);

		return url.toString();
	}

	public static String getFullURL(HttpServletRequest req) {

		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		String servletPath = req.getServletPath();
		String pathInfo = req.getPathInfo();
		String queryString = req.getQueryString();

		StringBuilder url = new StringBuilder();
		url.append(scheme).append("://").append(serverName);

		if (serverPort != 80 && serverPort != 443) {
			url.append(":").append(serverPort);
		}

		url.append(contextPath).append(servletPath);

		if (pathInfo != null) {
			url.append(pathInfo);
		}
		if (queryString != null) {
			url.append("?").append(queryString);
		}

		return url.toString();
	}

	private static String encodeValue(String value) {
	    try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
	    return "";
	}
	
	public static String getEncodeURL(String url, Map<String, String> requestParams) {
	    String encodedURL;
			encodedURL = requestParams.keySet().stream()
			  .map(key -> key + "=" + encodeValue(requestParams.get(key)))
			  .collect(Collectors.joining("&", url + "?", ""));
	 
	    return encodedURL;
	}
	
	private static String decode(String value) {
	    try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
	    return "";
	}
	 
	public static String getDencodeURL(String url) throws URISyntaxException {
	    URI uri = new URI(url);
	 
	    String scheme = uri.getScheme();
	    String host = uri.getHost();
	    String query = uri.getRawQuery();
	 
	    String decodedQuery = Arrays.stream(query.split("&"))
	      .map(param -> param.split("=")[0] + "=" + decode(param.split("=")[1]))
	      .collect(Collectors.joining("&"));
	 
	   return scheme + "://" + host + "?" + decodedQuery;
	}
	
	public static String encodePath(String pathSegment) {
        return UriUtils.encodePath(pathSegment, "UTF-8");
	}
	
	public static String decodePath(String encodedPathSegment) {
        return UriUtils.decode(encodedPathSegment, "UTF-8");
	}	
}
