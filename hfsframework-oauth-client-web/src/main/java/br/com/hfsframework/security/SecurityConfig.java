package br.com.hfsframework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.hfsframework.base.security.BaseOAuth2AuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	//@Autowired	
	//private HfsUserDetailsService hfsUserDetailsService;
	
	//@Autowired	
	//private LdapBundle ldapBundle;
	
	//@Autowired	
	//private BaseAuthenticationProvider baseAuthenticationProvider;
	
	//@Autowired
    //private BaseAuthenticationSuccessHandler successHandler;
	
	//@Autowired
	//private BaseAccessDeniedHandler accessDeniedHandler;	
	
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
		.antMatchers("/public/**").permitAll()
		.anyRequest().authenticated()	  	
        //.and()
        //.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
        .and()
		.formLogin()				
		.loginPage("/login")
			//.loginProcessingUrl("/j_spring_security_check")
			.successForwardUrl("/home")
			//.failureUrl("/login?error=400").permitAll()
			.failureUrl("/public/errorPage").permitAll()
			//.defaultSuccessUrl("/home")
			//.failureUrl("/errorPage").usernameParameter("username").passwordParameter("password")
			//.successHandler(successHandler)			
		.and()	
		.logout()
			//.invalidateHttpSession(true).deleteCookies("JSESSIONID")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login");
			//.logoutUrl("/logout").logoutSuccessUrl("/");
         //.and()
         //.sessionManagement().sessionFixation().none().maximumSessions(1).maxSessionsPreventsLogin(true);
    	
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

		

          auth
          .inMemoryAuthentication()
          .withUser(loginDTO.getLogin())
            .password(loginDTO.getSenha())
            .roles("USER", "ADMIN");
	*/		
		
		//auth
		 //.userDetailsService(this.hfsUserDetailsService)
			//.passwordEncoder(new BCryptPasswordEncoder());
		
		BaseOAuth2AuthenticationProvider baseAuthenticationProvider = new BaseOAuth2AuthenticationProvider();
		baseAuthenticationProvider.setInfo(env, "hfsframework");	
		auth.eraseCredentials(false);
		auth.authenticationProvider(baseAuthenticationProvider);
		
	}
	
    
    /*    
    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        return accessTokenResponseClient;
    }

    //@Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
    	BaseOAuth2Provider baseOAuth2Provider = new BaseOAuth2Provider();
        ClientRegistration registration = baseOAuth2Provider.getRegistration(env, "hfsframework");
        return new InMemoryClientRegistrationRepository(registration);
    }
    
    @Bean
    public RestTemplate restTemplate(OAuth2AuthorizedClientService clientService) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new AuthorizationHeaderInterceptor(clientService));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
*/ 
}
