package br.com.hfsframework.test;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.hfsframework.base.test.BaseOAuth2RestTest;
import br.com.hfsframework.config.TestWebConfig;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {TestWebConfig.class})
@TestInstance(Lifecycle.PER_CLASS)
public class AuthorizationServerTest extends BaseOAuth2RestTest {

	private static final Logger log = LogManager.getLogger(AuthorizationServerTest.class);
			
    @Autowired
    private JwtTokenStore tokenStore;
    
    @BeforeEach
    public void setup() throws Exception {
        super.setup("admin", "admin");
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
