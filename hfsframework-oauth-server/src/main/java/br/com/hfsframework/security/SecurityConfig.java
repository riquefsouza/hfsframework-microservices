package br.com.hfsframework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String REALM = "HFS_REALM";	
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private HfsUserDetailsService hfsUserDetailsService;	
	
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(hfsUserDetailsService)
    		.passwordEncoder(passwordEncoder);
	}	
    
	/*
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("hfsadmin").password(passwordEncoder.encode("hfspass")).roles("ADMIN","USER").and()
        .withUser("hfsuser").password(passwordEncoder.encode("123")).roles("USER");    
    }
    */
	

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {
    	
		http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
	  	.authorizeRequests()
	  	.antMatchers("/about").permitAll() 
        .antMatchers("/signup").permitAll()
	  	.antMatchers("/oauth/token").permitAll()
	  	//.antMatchers("/api/v1/**").authenticated()
        //.antMatchers("/api/v1/**").hasRole("USER")
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()	  	
        .anyRequest().authenticated()
	  	.and().httpBasic()
	  	.realmName(REALM);
    }

}
