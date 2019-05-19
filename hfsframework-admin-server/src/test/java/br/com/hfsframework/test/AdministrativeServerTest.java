package br.com.hfsframework.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import br.com.hfsframework.base.test.BaseOAuth2RestAssuredTest;
import br.com.hfsframework.config.TestWebConfig;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {TestWebConfig.class})
@TestInstance(Lifecycle.PER_CLASS)
public class AdministrativeServerTest extends BaseOAuth2RestAssuredTest {

	private static final Logger log = LogManager.getLogger(AdministrativeServerTest.class);
	
	//private static final String HFSFRAMEWORK_ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server";
	
    @Autowired
    private JwtTokenStore tokenStore;

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
}
