package br.com.hfsframework.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import br.com.hfsframework.util.network.ResourceUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//private static String REALM = "HFS_REALM";

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {

    	http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.requestMatchers().antMatchers(ResourceUtil.resourceSwagger())
		.requestMatchers().antMatchers("/api/public/**")
		.and()
		.authorizeRequests()
		.antMatchers("/api/public/**").permitAll()
		.antMatchers("/api/v1/**").permitAll()
		//.antMatchers("/api/v1/**").access("hasRole('USER') or hasRole('ADMIN')")
	  	//.antMatchers("/api/v1/**").authenticated()
        //.antMatchers("/api/v1/**").hasRole("ADMIN")
        //.antMatchers("/api/v1/**").hasRole("USER")
        .anyRequest().authenticated()
	  	.and().httpBasic();
	  	//.realmName(REALM);
    }

	
	
}
