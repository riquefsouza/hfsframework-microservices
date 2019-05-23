package br.com.hfsframework.util.interceptors;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class BaseHeaderRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger log = LoggerFactory.getLogger(BaseHeaderRequestInterceptor.class);

	private final String headerName;

	private final String headerValue;

	public BaseHeaderRequestInterceptor(String headerName, String headerValue) {
		this.headerName = headerName;
		this.headerValue = headerValue;

		log.info("BaseHeaderRequestInterceptor: " + headerName + " - " + headerValue);
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		request.getHeaders().set(headerName, headerValue);

		log.info("intercept: " + headerName + " - " + headerValue);
		
		return execution.execute(request, body);
	}
}
