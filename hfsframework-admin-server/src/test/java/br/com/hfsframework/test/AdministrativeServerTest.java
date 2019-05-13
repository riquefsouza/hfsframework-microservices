package br.com.hfsframework.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.hfsframework.config.TestWebConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {TestWebConfig.class})
@TestInstance(Lifecycle.PER_CLASS)
public class AdministrativeServerTest {

	private static final Logger log = LogManager.getLogger(AdministrativeServerTest.class);
	
	private static final String HFSFRAMEWORK_OAUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth/token";
	private static final String HFSFRAMEWORK_ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server";
	
    @Autowired
    private JwtTokenStore tokenStore;

    private static final String CLIENT_ID = "hfsClient";
    private static final String CLIENT_SECRET = "hfsSecret";
    
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private String obtainAccessToken(String username, String password) {
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
        		.when().post(HFSFRAMEWORK_OAUTH_SERVER);
        return response.jsonPath().getString("access_token");
        
    }
 
    @SuppressWarnings("unchecked")
	@Test
    public void whenTokenDontContainIssuer_thenSuccess() {
        final String tokenValue = obtainAccessToken("admin", "admin");
        final OAuth2Authentication auth = tokenStore.readAuthentication(tokenValue);
        log.info("TOKE VALUE: " + tokenValue);
        log.info("OAuth2: " + auth);
        assertTrue(auth.isAuthenticated());
        log.info("OAuth2 Details: " + auth.getDetails());

        Map<String, Object> details = (Map<String, Object>) auth.getDetails();
        assertTrue(details.containsKey("authorities"));
        log.info("Authorities: " + details.get("authorities"));
        assertTrue(details.containsKey("organization"));
        log.info("Organization: " + details.get("organization"));
    }
 
    @Test
    public void givenUser_whenUseHfsClient_thenOkForAdminServer() {
        final String accessToken = obtainAccessToken("admin", "admin");

        final Response response = RestAssured.given()
        		.header("Authorization", "Bearer " + accessToken)
        		.get(HFSFRAMEWORK_ADMIN_SERVER + "/api/v1/parametroCategoria/1");
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.jsonPath().get("descricao"));
        log.info("DESCRICAO: " + response.jsonPath().get("descricao"));
    }
    
    @Test
    public void givenUser_whenUseHfsClient_thenOkForParametro() {
        final String accessToken = obtainAccessToken("admin", "admin");

        final Response response = RestAssured.given()
        		.header("Authorization", "Bearer " + accessToken)
        		.get(HFSFRAMEWORK_ADMIN_SERVER + "/api/v1/parametro");
        assertEquals(200, response.getStatusCode());
        //assertNotNull();
        //log.info("PARAMETRO: " + );
        response.jsonPath().prettyPrint();
    }
}
