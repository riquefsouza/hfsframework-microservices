package br.com.hfsframework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.hfsframework.security.login.StatelessAuthenticationFilter;
import br.com.hfsframework.security.login.StatelessLoginFilter;
import br.com.hfsframework.security.login.TokenAuthenticationService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String REALM = "HFS_REALM";	
	
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private HfsUserDetailsService hfsUserDetailsService;
	
	private UserDetailsService userDetailsService;
	private TokenAuthenticationService tokenAuthenticationService;
	
	public SecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = new TokenAuthenticationService("hfsSecret", userDetailsService);
	}
	
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
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
        return passwordEncoder;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {
    	
		StatelessLoginFilter statelessLoginFilter = new StatelessLoginFilter("/api/login", 
				tokenAuthenticationService, userDetailsService, authenticationManager());
		
		StatelessAuthenticationFilter statelessAuthenticationFilter = 
				new StatelessAuthenticationFilter(tokenAuthenticationService);
    	
		http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.addFilterBefore(statelessLoginFilter, UsernamePasswordAuthenticationFilter.class)		
		.addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)		
		.requestMatchers().antMatchers("/api/public/**")
		.requestMatchers().antMatchers(HttpMethod.POST, "/api/login")
		.and()
		.authorizeRequests()
	  	.antMatchers("/oauth/token").permitAll()
		.antMatchers("/oauth/token/revokeById/**").permitAll()
		.antMatchers("/tokens/**").permitAll()	  	
        .anyRequest().authenticated()
	  	.and().httpBasic()
	  	.realmName(REALM);
    }

    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }    
    
}
