package br.com.hfsframework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired	
	private HfsUserDetailsService hfsUserDetailsService;
	
	//@Autowired	
	//private LdapBundle ldapBundle;
	
	@Autowired
    private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {

    	http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/css/**", "/img/**", "/js/**", "/primeui/**", "/scss/**", "/vendor/**").permitAll()
	  	.antMatchers("/sobre").permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
        .and()
		.formLogin()				
		.loginPage("/login")
			.loginProcessingUrl("/j_spring_security_check")
			.successForwardUrl("/home")
			//.defaultSuccessUrl("/home")
			.failureUrl("/?error").usernameParameter("username").passwordParameter("password")
			.successHandler(successHandler)
		.and()	
		.logout()
			.invalidateHttpSession(true).deleteCookies("JSESSIONID")
			//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutUrl("/logout").logoutSuccessUrl("/")
         .and()
         .sessionManagement().sessionFixation().none().maximumSessions(1).maxSessionsPreventsLogin(true);
         
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		String surl = ldapBundle.getLdapTipoConexao().toLowerCase()+"://"+ldapBundle.getLdapServer()+":"+ldapBundle.getLdapPorta();
		
		auth
		.ldapAuthentication()
			.userDnPatterns(ldapBundle.getLdapUserDN())
			.groupSearchBase(ldapBundle.getLdapBaseDN())
			.contextSource()				
				.url(surl)
				.and()
			.passwordCompare()
				//.passwordEncoder(new LdapShaPasswordEncoder())
				.passwordAttribute("userPassword");

		*/
		
		auth
		.userDetailsService(this.hfsUserDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());			
		
	}
}
