package br.com.hfsframework.base.view;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.hfsframework.security.model.MenuVO;
import br.com.hfsframework.security.model.UserAuthenticatedVO;

public abstract class BaseViewController {

	protected transient Logger log;	
	
	protected static final String SELECIONAR_REGISTRO = "Please select a table record to do this action!";
	
	protected static final String ERRO_SALVAR = "Transaction Error When Saving: ";
	
	protected static final String ERRO_DELETE = "Error Transaction When Excluding: ";
	
	public String getDesktopPage(){
		return "/";
	}

	public void generateErrorMessage(String message) {
	}

	public void generateErrorMessage(Exception e, String message) {
	}

	public void generateInfoMessage(String message) {
	}

	public void generateWarningMessage(String mensagem) {
	}

	public static void addMessageInfoDialog(String mensagem) {
	}

	public static void addMessageAlertaDialog(String mensagem) {
	}

	public static void addMessageErroDialog(Exception e, String mensagem) {
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
}
