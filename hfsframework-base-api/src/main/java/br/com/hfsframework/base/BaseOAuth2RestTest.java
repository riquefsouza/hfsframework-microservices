package br.com.hfsframework.base;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

public abstract class BaseOAuth2RestTest {

	private static final String HFSFRAMEWORK_OAUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth/token";
	//private static final String HFSFRAMEWORK_ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server";

    private static final String CLIENT_ID = "hfsClient";
    private static final String CLIENT_SECRET = "hfsSecret";

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("UTF-8"));

    protected MockMvc mockMvc;
    
    protected HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected FilterChainProxy springSecurityFilterChain;

    protected String accessToken;
    
    /*
    @Autowired
    protected void setConverters(HttpMessageConverter<Object>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull(this.mappingJackson2HttpMessageConverter, 
        		"the JSON message converter must not be null");
    }
    */
    
	protected String json(Object o) throws IOException {
	    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	    this.mappingJackson2HttpMessageConverter.write(
	            o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	    return mockHttpOutputMessage.getBodyAsString();
	}

    protected String obtainAccessToken(String username, String password) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", CLIENT_ID);
        params.add("username", username);
        params.add("password", password);

        ResultActions result = mockMvc.perform(
        		post(HFSFRAMEWORK_OAUTH_SERVER)
       					.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
       					.params(params)
       					.with(httpBasic(CLIENT_ID, CLIENT_SECRET))
       					.accept(CONTENT_TYPE))
        		.andExpect(status().isOk())
        		.andExpect(content().contentType(CONTENT_TYPE));
        
        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
    
	
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
        accessToken = obtainAccessToken("admin", "admin");
    }
	

}
