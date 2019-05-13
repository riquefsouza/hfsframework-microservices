package br.com.hfsframework.test;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import br.com.hfsframework.config.TestWebConfig;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {TestWebConfig.class})
@TestInstance(Lifecycle.PER_CLASS)
public class AuthorizationServerTest {

	private static final Logger log = LogManager.getLogger(AuthorizationServerTest.class);
			
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    private static final String CLIENT_ID = "hfsClient";
    private static final String CLIENT_SECRET = "hfsSecret";

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
 
    @Autowired
    private JwtTokenStore tokenStore;
    
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }
    
    private String obtainAccessToken(String username, String password) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", CLIENT_ID);
        params.add("username", username);
        params.add("password", password);

        ResultActions result = mockMvc.perform(
        		post("/oauth/token")
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
    
	@Test
    public void whenTokenDontContainIssuer_thenSuccess() throws Exception {
    	final String accessToken = obtainAccessToken("admin", "admin");
        final OAuth2Authentication auth = tokenStore.readAuthentication(accessToken);
        log.info("TOKE VALUE: " + accessToken);
        log.info("OAuth2: " + auth);
        assertTrue(auth.isAuthenticated());
        log.info("OAuth2 Authorities: " + auth.getAuthorities());
        //log.info("OAuth2 Details: " + auth.getDetails());

        //Map<String, Object> details = (Map<String, Object>) auth.getDetails();
        //assertTrue(details.containsKey("authorities"));
        //log.info("Authorities: " + details.get("authorities"));
        //assertTrue(details.containsKey("organization"));
        //log.info("Organization: " + details.get("organization"));
    }
    
    @Test
    public void getCustomers_thenOk() throws Exception {
        final String accessToken = obtainAccessToken("admin", "admin");

        mockMvc.perform(get("/api/v1/customers")
                .header("Authorization", "Bearer " + accessToken)
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$[0]['name']", is("Google Inc")));
        
    }
    
    @Test
    public void postSignup_thenOk() throws Exception {
        String signup_raw = "{\"username\": \"hfsuser\",\"password\": \"hfspass\"}";
        
        mockMvc.perform(post("/signup")
                .contentType(CONTENT_TYPE)
                .content(signup_raw)
                .accept(CONTENT_TYPE))
                .andExpect(status().isCreated());

        final String accessToken = obtainAccessToken("hfsuser", "hfspass");
        assertTrue(accessToken.length() > 600);
    }
}
