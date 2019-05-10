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
	
	//private static String REALM = "HFS_REALM";	

	/*
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    */

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {
    	
		http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.requestMatchers().antMatchers("/swagger*", "/v2/**").and()
		.authorizeRequests()
	  	.antMatchers("/sobre").permitAll() 
	  	//.antMatchers("/api/v1/**").authenticated()
        //.antMatchers("/api/v1/**").hasRole("USER")
        .anyRequest().authenticated();
	  	//.and().httpBasic();
	  	//.realmName(REALM);
    }

	
	
}
