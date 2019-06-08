package br.com.hfsframework.base.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class BaseHeaderClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger log = LoggerFactory.getLogger(BaseHeaderClientHttpRequestInterceptor.class);

	private final String headerName;

	private final String headerValue;

	public BaseHeaderClientHttpRequestInterceptor(String headerName, String headerValue) {
		this.headerName = headerName;
		this.headerValue = headerValue;

		log.info("BaseHeaderClientHttpRequestInterceptor: " + headerName + " - " + headerValue);
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		request.getHeaders().set(headerName, headerValue);

		log.info("intercept: " + headerName + " - " + headerValue);

		logRequest(request, body);

		ClientHttpResponse response = execution.execute(request, body);

		logResponse(response);

		return response;
	}

	private void logRequest(HttpRequest request, byte[] body) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("REQUEST BEGIN");
			log.debug("URI         : {}", request.getURI());
			log.debug("Method      : {}", request.getMethod());
			log.debug("Headers     : {}", request.getHeaders());
			log.debug("Request body: {}", new String(body, "UTF-8"));
			log.debug("REQUEST END");
		}
	}

	private void logResponse(ClientHttpResponse response) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("RESPONSE BEGIN");
			log.debug("Status code  : {}", response.getStatusCode());
			log.debug("Status text  : {}", response.getStatusText());
			log.debug("Headers      : {}", response.getHeaders());
			log.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
			log.debug("RESPONSE END");
		}
	}
}
