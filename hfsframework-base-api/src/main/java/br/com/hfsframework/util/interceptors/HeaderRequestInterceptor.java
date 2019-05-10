/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.util.interceptors;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class HeaderRequestInterceptor.
 */
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

	/** The header name. */
	private final String headerName;

	/** The header value. */
	private final String headerValue;

	/**
	 * Instantiates a new header request interceptor.
	 *
	 * @param headerName the header name
	 * @param headerValue the header value
	 */
	public HeaderRequestInterceptor(String headerName, String headerValue) {
		this.headerName = headerName;
		this.headerValue = headerValue;
		
		System.out.println("HeaderRequestInterceptor: " + headerName + " - " + headerValue);
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		request.getHeaders().set(headerName, headerValue);
		
		System.out.println("intercept: " + headerName + " - " + headerValue);
		
		return execution.execute(request, body);
	}
}
