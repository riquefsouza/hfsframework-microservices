package br.com.hfsframework.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unbescape.html.HtmlEscape;

import br.com.hfsframework.base.view.BaseViewController;

@Controller
@RequestMapping("/")
//@SessionAttributes("roles")
public class AppController extends BaseViewController {

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
	
	@RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/index.html";
    }

    /** Home page. */
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }
    
    /** Login form. */
    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
        
    /** Error page. */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /** Error page. */
    @RequestMapping("/403.html")
    public String forbidden() {
        return "403";
    }
	
    /*
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		
		if (getPrincipal().isPresent()) {
			model.addAttribute("", getPrincipal().get().getUsername());
		}
		
		return "accessDenied";
	}
*/
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
