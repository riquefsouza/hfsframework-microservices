package br.com.hfsframework.controller;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.hfsframework.base.security.BaseOAuth2RestUser;

@Controller
@RequestMapping("/")
//@SessionAttributes("roles")
public class AppController {

	//@Autowired
	//private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	//@Autowired
	//private AuthenticationTrustResolver authenticationTrustResolver;

	/*
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	*/
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		
		if (getPrincipal().isPresent()) {
			model.addAttribute("loggedinuser", getPrincipal().get().getUsername());
		}
		
		return "accessDenied";
	}

	/*
	@RequestMapping(value = "/perform_login", method = RequestMethod.GET)
	public String loginPage() {
		//if (isCurrentAuthenticationAnonymous()) {
			//return "login";
	    //} else {
	    	//return "redirect:/list";  
	    //}
		
		return "homepage";
	}
	*/
	
	private Optional<BaseOAuth2RestUser> getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null){ 
			Object principal = authentication.getPrincipal();
			
			if (principal instanceof BaseOAuth2RestUser) {
				BaseOAuth2RestUser userLogged = (BaseOAuth2RestUser) principal;
				//String sUrlAuthServer = userLogged.getUrlAuthorizationServer();
				//String sToken = userLogged.getAccessToken().getValue();
				
				return Optional.of(userLogged);
			}
		}
		return Optional.empty();
	}
	
	/*
	@RequestMapping(value="/perform_logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
		
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
	 */

}
