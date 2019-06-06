package br.com.hfsframework.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String REALM = "HFS_REALM";

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {

    	http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		//.requestMatchers().antMatchers("/api/public/**") //, "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**", "/configuration/**")
		//.and()
		.authorizeRequests()
		.antMatchers("/api/public/**").permitAll()
		//, "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**", "/configuration/**").permitAll()
		//.antMatchers("/api/v1/**").access("hasRole('USER') or hasRole('ADMIN')")
	  	//.antMatchers("/api/v1/**").authenticated()
        //.antMatchers("/api/v1/**").hasRole("ADMIN")
        //.antMatchers("/api/v1/**").hasRole("USER")
        .anyRequest().authenticated()
	  	.and().httpBasic()
	  	.realmName(REALM);	  	  
    }

	
	
}
