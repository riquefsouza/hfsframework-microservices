package br.com.hfsframework.security;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.DelegatingJwtClaimsSetVerifier;
import org.springframework.security.oauth2.provider.token.store.IssuerClaimVerifier;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import br.com.hfsframework.base.security.BaseAccessTokenConverter;
import br.com.hfsframework.base.security.BaseClaimVerifier;
import br.com.hfsframework.util.network.ResourceUtil;

@Configuration
@PropertySource("classpath:application.properties")
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${oauth2.hfsframework.server}")
	private String authServer;

    @Autowired
    private BaseAccessTokenConverter customAccessTokenConverter;

    private TokenStore tokenStore;
    
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		anonymous().disable()
		.requestMatchers().antMatchers(ResourceUtil.resourceSwagger())
		.requestMatchers().antMatchers("/api/public/**")
		.and()
		.authorizeRequests()
		//.antMatchers("/api/v1/**").access("hasRole('ADMIN') or hasRole('USER')")
		.antMatchers(ResourceUtil.resourceSwagger()).permitAll()
		.antMatchers("/api/public/**").permitAll()
		.antMatchers("/api/v1/**").permitAll()
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
	
    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) {
    	resources.tokenStore(tokenStore());
    }

    @Bean
    public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }
    
    @Bean
    public TokenStore tokenStore() {
    	if (tokenStore == null) {
            tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        }
        return tokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);
        converter.setJwtClaimsSetVerifier(jwtClaimsSetVerifier());
        //converter.setSigningKey("123");
		converter.setVerifierKey(getPublicKeyAsString());
		return converter;
    }

    private String getPublicKeyAsString() {
		final Resource resource = new ClassPathResource("public.txt");
		String publicKey = null;
		try {
			publicKey = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		return publicKey;
    }
    
    @Bean
    public JwtClaimsSetVerifier jwtClaimsSetVerifier() {
        return new DelegatingJwtClaimsSetVerifier(Arrays.asList(issuerClaimVerifier(), customJwtClaimVerifier()));
    }

    @Bean
    public JwtClaimsSetVerifier issuerClaimVerifier() {
        try {
            return new IssuerClaimVerifier(new URL(authServer));
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public JwtClaimsSetVerifier customJwtClaimVerifier() {
        return new BaseClaimVerifier();
    }
    
}
