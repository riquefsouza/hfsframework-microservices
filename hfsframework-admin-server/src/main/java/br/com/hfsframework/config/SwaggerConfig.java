package br.com.hfsframework.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@PropertySource("classpath:application.properties")
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${oauth2.hfsframework.server}")
	private String authServer;

	@Value("${oauth2.hfsframework.client-id}")
	private String authClientId;
	
	@Value("${oauth2.hfsframework.client-secret}")
	private String authClientSecret;
	
/*
	
	public static final String AUTH_SERVER = "http://localhost:8080/hfsframework-oauth-server/oauth";
	public static final String CLIENT_ID = "hfsClient";
	public static final String CLIENT_SECRET = "hfsSecret";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()));
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder().clientId(CLIENT_ID).clientSecret(CLIENT_SECRET)
				.useBasicAuthenticationWithAccessCodeGrant(true).build();
	}

	private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder()
				.tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
				.tokenRequestEndpoint(new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
				.build();

		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth").grantTypes(Arrays.asList(grantType))
				.scopes(Arrays.asList(scopes())).build();
		return oauth;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
				.forPaths(PathSelectors.regex("/api/v1.*")).build();
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { new AuthorizationScope("read", "for read operations"),
				new AuthorizationScope("write", "for write operations"),
				new AuthorizationScope("API Admin V1", "Access Admin API") };
		return scopes;
	}
	
*/	
	private String serviceName = "HFS Framework Admin";

	private String serviceDesc = "HFS Framework Admin using springframework";

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("HFSFramework Admin Server")

				//.pathProvider(pathProvider())
                //.host(hostName)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.hfsframework.admin"))				
				//.apiInfo(apiInfo()).select().paths(postPaths())
				//.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))	
				//.paths(springBootActuatorJmxPaths())
				.paths(regex("/api.*"))
				//.apiInfo(apiInfo()).select().paths(PathSelectors.any())
				//.apis(RequestHandlerSelectors.any())
				.build();
				//.securitySchemes(newArrayList(oauth()))
				//.securitySchemes(Collections.singletonList(oauth()));
				//.securityContexts(newArrayList(securityContext()));
	}

	/*
	private Predicate<String> postPaths() {
		return regex("/.*");
	}   

	private Predicate<String> springBootActuatorJmxPaths() {
		return regex("^/(?!env|restart|pause|resume|refresh).*$");
	} 
	 */
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(serviceName).description(serviceDesc).build();
	}	
	
	/*
	@Bean
	List<GrantType> grantTypes() {
		List<GrantType> grantTypes = new ArrayList<>();
		//TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(authServer+"/oauth/authorize", authClientId, authClientSecret );
        //TokenEndpoint tokenEndpoint = new TokenEndpoint(authServer+"/oauth/token", "token");
		//grantTypes.add(new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint));
        grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(authServer+"/oauth/token"));
		//grantTypes.add(new ClientCredentialsGrant(authServer+"/oauth/token"));
		
		//LoginEndpoint loginEndpoint = new LoginEndpoint(url);
		//grantTypes.add(new ImplicitGrant(loginEndpoint, tokenName));
				
        return grantTypes;
	}
	
	@Bean
    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("OAuth2")
                .scopes(scopes())
                .grantTypes(grantTypes())
                .build();
    }
	
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
    	
        //AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        //authorizationScopes[0] = new AuthorizationScope("write", "write and read");
        //authorizationScopes[1] = new AuthorizationScope("read", "read only");
        //authorizationScopes[2] = new AuthorizationScope("trust","Grants read write and delete access");
        
    	AuthorizationScope[] authorizationScopes = scopes()
    			.stream()
    			.toArray(AuthorizationScope[]::new);        
        return newArrayList(new SecurityReference("OAuth2", authorizationScopes));
    }
	
	private List<AuthorizationScope> scopes() {
		List<AuthorizationScope> list = new ArrayList<>();
		list.add(new AuthorizationScope("write", "write and read"));
		list.add(new AuthorizationScope("read", "read only"));
		list.add(new AuthorizationScope("trust","Grants read write and delete access"));
		//list.add(new AuthorizationScope("read_scope","Grants read access"));
		//list.add(new AuthorizationScope("write_scope","Grants write access"));
		//list.add(new AuthorizationScope("admin_scope","Grants read write and delete access"));
		
		return list;
    }	

	@Bean
    public SecurityConfiguration securityInfo() {
		return SecurityConfigurationBuilder.builder()
		        .clientId(authClientId)
		        .clientSecret(authClientSecret)
		        .scopeSeparator(",")
		        .useBasicAuthenticationWithAccessCodeGrant(true)
		        .build();
	}
	*/
}
