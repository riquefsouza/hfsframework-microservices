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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.hfsframework.util.ldap.LdapBundle;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired	
	private HfsUserDetailsService hfsUserDetailsService;
	
	@Autowired	
	private LdapBundle ldapBundle;

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
		.formLogin()				
		.loginPage("/login")
			.failureUrl("/login?error=401").permitAll()
			.successForwardUrl("/home").and()
			//.defaultSuccessUrl("/home").and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login");

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
