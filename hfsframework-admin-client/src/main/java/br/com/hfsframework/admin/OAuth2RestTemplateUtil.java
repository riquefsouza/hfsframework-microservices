package br.com.hfsframework.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

public final class OAuth2RestTemplateUtil {

	//private static final Logger log = LogManager.getLogger(OAuth2RestTemplateUtil.class);
	
	/*
	private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;
	
    @Autowired
    protected void setConverters(HttpMessageConverter<Object>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        if (this.mappingJackson2HttpMessageConverter==null) {
        	log.error("the JSON message converter must not be null");
        }
    }
    */
	
	public static OAuth2RestTemplate restTemplate(String server, String login, String password) {
		
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setGrantType("password");
		resourceDetails.setAccessTokenUri(server);
		resourceDetails.setClientId("hfsClient");
		resourceDetails.setClientSecret("hfsSecret");

		List<String> scopes = new ArrayList<String>();
		scopes.add("read");
		scopes.add("write");
		scopes.add("trust");
		resourceDetails.setScope(scopes);

		resourceDetails.setUsername(login);
		resourceDetails.setPassword(password);

		OAuth2RestTemplate rt = new OAuth2RestTemplate(resourceDetails);
		//rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		//rt.getMessageConverters().add(new StringHttpMessageConverter());
		
		return rt;
	}

}
