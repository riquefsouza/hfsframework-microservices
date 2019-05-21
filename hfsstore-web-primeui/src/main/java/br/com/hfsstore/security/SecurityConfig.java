package br.com.hfsstore.security;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import br.com.hfsframework.base.security.BaseAccessDeniedHandler;
import br.com.hfsframework.base.security.BaseAuthenticationFailureHandler;
import br.com.hfsframework.base.security.BaseLogoutSuccessHandler;
import br.com.hfsframework.base.security.BaseOAuth2AuthenticationProvider;

@Configuration
// @ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
@Profile("!https")
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

    public SecurityConfig() {
        super();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		BaseOAuth2AuthenticationProvider baseAuthenticationProvider = new BaseOAuth2AuthenticationProvider();
		baseAuthenticationProvider.setInfo(env, "hfsframework");	
		auth.eraseCredentials(false);
		auth.authenticationProvider(baseAuthenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/css/**", "/img/**", "/js/**", "/primeui/**", "/scss/**", "/vendor/**").permitAll()
		.antMatchers("/private/admin/**").hasRole("ADMIN")
		.antMatchers("/private/**").hasRole("USER")
		.antMatchers("/anonymous*").anonymous()
		.antMatchers("/login*").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login.html")
		.loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/homepage.html", true)
		//.failureUrl("/login.html?error=true")
		.failureHandler(authenticationFailureHandler())
		.and()
		.logout()
		.logoutUrl("/perform_logout")
		.deleteCookies("JSESSIONID")
		.logoutSuccessHandler(logoutSuccessHandler());
        //.and()
        //.exceptionHandling().accessDeniedPage("/accessDenied");
        //.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

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
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new BaseAuthenticationFailureHandler();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
