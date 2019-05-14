package br.com.hfsframework.base;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public abstract class BaseOAuth2RestAssuredTest {

    private static final String CLIENT_ID = "hfsClient";
    private static final String CLIENT_SECRET = "hfsSecret";

    protected static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    
	private static final String HFSFRAMEWORK_OAUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth/token";
	
	private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;
	
	protected String accessToken;
    
    @Autowired
    protected void setConverters(HttpMessageConverter<Object>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull(this.mappingJackson2HttpMessageConverter, 
        		"the JSON message converter must not be null");
    }
	
	protected String json(Object o) throws IOException {
	    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	    this.mappingJackson2HttpMessageConverter.write(
	            o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	    return mockHttpOutputMessage.getBodyAsString();
	}
	
    protected String obtainAccessToken(String username, String password) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", CLIENT_ID);
        params.add("username", username);
        params.add("password", password);

        final Response response = RestAssured.given()
        		.auth().preemptive().basic(CLIENT_ID, CLIENT_SECRET)
        		.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        		.params(params)
        		.accept(CONTENT_TYPE)
        		//.accept(ContentType.JSON)
        		.when().post(HFSFRAMEWORK_OAUTH_SERVER);
        return response.jsonPath().getString("access_token");
        
    }

}
