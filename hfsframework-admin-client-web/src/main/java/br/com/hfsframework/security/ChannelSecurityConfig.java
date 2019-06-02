package br.com.hfsframework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import br.com.hfsframework.base.security.BaseAccessDeniedHandler;
import br.com.hfsframework.base.security.BaseAuthenticationFailureHandler;
import br.com.hfsframework.base.security.BaseAuthenticationSuccessHandler;
import br.com.hfsframework.base.security.BaseLogoutSuccessHandler;
import br.com.hfsframework.base.security.BaseOAuth2AuthenticationProvider;
import br.com.hfsframework.util.CookieUtil;
import br.com.hfsframework.util.ResourceUtil;

@Configuration
@EnableWebSecurity
@Profile("https")
@PropertySource("classpath:application.properties")
public class ChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	/*
	@Autowired
	private PersistentTokenRepository tokenRepository;
	*/

    public ChannelSecurityConfig() {
        super();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BaseOAuth2AuthenticationProvider baseAuthenticationProvider = new BaseOAuth2AuthenticationProvider();
		baseAuthenticationProvider.setInfo(env, "hfsframework");	
		auth.eraseCredentials(false);
		auth.authenticationProvider(baseAuthenticationProvider);
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(ResourceUtil.resourceHandler()).permitAll()       
		.antMatchers("/public/**").permitAll()
		.antMatchers("/private/**").access("hasRole('USER') or hasRole('ADMIN')")
		.antMatchers("/anonymous*").anonymous()
		.antMatchers("/login*").permitAll()
		.anyRequest().authenticated()
		.and()
		//Begin Channel Secure Config
		.requiresChannel()
        .antMatchers("/login*", "/perform_login").requiresSecure()
        .anyRequest().requiresInsecure()
        .and()
        .sessionManagement()
        .sessionFixation()
        .none()
        .and()		
		//End Channel Secure Config
		.formLogin()
		.loginPage("/login.html")
		//.loginProcessingUrl("/perform_login")
		//.defaultSuccessUrl("/homepage.html", true)
		.successHandler(authenticationSuccessHandler())
		//.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
		.failureUrl("/login-error.html")	
		//.failureHandler(authenticationFailureHandler())
		//.and()
		//.rememberMe().rememberMeParameter("remember-me")
		.and()
		.logout()
		//.logoutUrl("/perform_logout")
		.deleteCookies(CookieUtil.JSESSIONID)
		//.logoutSuccessHandler(logoutSuccessHandler())
		.logoutSuccessUrl("/index.html")
        .and()
        //.exceptionHandling().accessDeniedPage("/private/accessDenied");
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new BaseLogoutSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new BaseAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
    	return new BaseAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new BaseAuthenticationFailureHandler();
    }

    /*
	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
	*/
}
