package br.com.hfsframework.base.view;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hfsframework.base.security.BaseOAuth2RestUser;
import br.com.hfsframework.security.model.MenuVO;
import br.com.hfsframework.security.model.UserAuthenticatedVO;

public abstract class BaseViewController {

	protected transient Logger log;	
	
	protected static final String SELECIONAR_REGISTRO = "Please select a table record to do this action!";
	
	protected static final String ERRO_SALVAR = "Transaction Error When Saving: ";
	
	protected static final String ERRO_DELETE = "Error Transaction When Excluding: ";
	
	protected String authServerURL;
	
	protected String accesToken;
	
	@Autowired
	protected MessageSource messageSource;
	
	public String getDesktopPage(){
		return "/index.html";
	}

	public void showValidationMessage(RedirectAttributes attributes, String messageCode) {
		attributes.addFlashAttribute("validationMessage", messageSource.getMessage(messageCode, null, Locale.getDefault()));
	}

	public void showErrorMessage(RedirectAttributes attributes, Exception e) {
		attributes.addFlashAttribute("errorMessage", e.getMessage());
	}

	public void generateErrorMessage(String mensagem) {
	}

	public void generateErrorMessage(Exception e, String mensagem) {
	}

	public void generateInfoMessage(String mensagem) {
	}

	public void gerarMensagemAviso(String mensagem) {
	}

	public void generateErrorMessage(Exception e, String mensagem, String clientId) {
	}

	public void generateInfoMessage(String mensagem, String clientId) {
	}

	public void generateWarnMessage(String mensagem, String clientId) {
	}
	
	public static void addMessageInfoDialog(String mensagem) {
	}

	public static void addMessageWarnDialog(String mensagem) {
	}

	public static void addMessageErrorDialog(Exception e, String mensagem) {
	}
		
	public HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession();
		
	}
	
	public Map<String, String> getSessionAttributes() {
		HttpSession sessao = getSession();
		HashMap<String, String> mapa = new HashMap<String, String>();
		String atributo, valor;
		for (Enumeration<String> item = sessao.getAttributeNames(); item.hasMoreElements();) {
			atributo = item.nextElement();
			valor = sessao.getAttribute(atributo).toString();
			mapa.put(atributo, valor);
		}
		return mapa;
	}
	
	public void logSessionAttributes() {
		log.info("Sessão: [");
		for (Entry<String, String> item : getSessionAttributes().entrySet()) {
			log.info("\n" + item.getKey() + " = " + item.getValue());
		}
		log.info("]");
	}
	
	public UserAuthenticatedVO getUserAuthenticated() {		
		return (UserAuthenticatedVO) getSession().getAttribute("userAuthenticated");
	}
	
	public void setUserAuthenticated(UserAuthenticatedVO usu){
		getSession().setAttribute("userAuthenticated", usu);
	}
	
	private String getIdMenu() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();		
		Map<String, String[]> params = attr.getRequest().getParameterMap();
		String[] sIdMenu = params.get("id");
		if (sIdMenu != null && sIdMenu.length > 0 && !sIdMenu[0].isEmpty()) {
			return sIdMenu[0];
		}
		return "";
	}
	
	public MenuVO getCurrentMenu(){
		String idMenu = getIdMenu();
		if (!idMenu.isEmpty())
			return getUserAuthenticated().getMenu(idMenu);
		else 
			return null;
	}
	
	public Optional<BaseOAuth2RestUser> getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null){ 
			Object principal = authentication.getPrincipal();
			
			if (principal instanceof BaseOAuth2RestUser) {
				BaseOAuth2RestUser userLogged = (BaseOAuth2RestUser) principal;
				return Optional.of(userLogged);
			}
		}
		return Optional.empty();
	}
	
	public Optional<ModelAndView> getPage(String pagina) {		
		if (getPrincipal().isPresent()) {
			ModelAndView mv = new ModelAndView(pagina);

			this.authServerURL = this.getPrincipal().get().getUrlAuthorizationServer();
			this.accesToken = this.getPrincipal().get().getAccessToken().getValue();
		
			mv.addObject("urlAuthServer", authServerURL);
			mv.addObject("authToken", accesToken);
			return Optional.of(mv);
		}		
		return Optional.empty();
	}	
	
}
