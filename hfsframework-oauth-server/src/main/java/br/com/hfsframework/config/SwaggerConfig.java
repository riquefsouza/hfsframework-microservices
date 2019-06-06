package br.com.hfsframework.config;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@PropertySource("classpath:application.properties")
@EnableSwagger2
public class SwaggerConfig {

	private String serviceName = "HFS Framework OAuth Authorization Server";

	private String serviceDesc = "HFS Framework OAuth Authorization Server";

	@Value("${oauth2.hfsframework.server}")
	private String authServer;

	@Value("${oauth2.hfsframework.client-id}")
	private String authClientId;
	
	@Value("${oauth2.hfsframework.client-secret}")
	private String authClientSecret;
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("HFS Framework OAuth Server")
				//.apiInfo(apiInfo()).select().paths(postPaths())
				//.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))	
				//.paths(springBootActuatorJmxPaths())
				.apiInfo(apiInfo()).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.any())
				.build()		
				.securitySchemes(newArrayList(oauth()))
				//.securitySchemes(Collections.singletonList(oauth()));
				.securityContexts(newArrayList(securityContext()));
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
	
	@Bean
	List<GrantType> grantTypes() {
		List<GrantType> grantTypes = new ArrayList<>();
		//TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(authServer+"/oauth/authorize", clientId, clientSecret );
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
    	/*
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("write", "write and read");
        authorizationScopes[1] = new AuthorizationScope("read", "read only");
        authorizationScopes[2] = new AuthorizationScope("trust","Grants read write and delete access");
        */
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
	
}
