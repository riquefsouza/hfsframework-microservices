/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
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
import br.com.hfsframework.security.model.UsuarioAutenticadoVO;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseViewController.
 */
public abstract class BaseViewController {

	/** The log. */
	protected transient Logger log;	
	
	/** The Constant SELECIONAR_REGISTRO. */
	protected static final String SELECIONAR_REGISTRO = "Favor selecionar um registro da tabela para proceder esta ação.";
	
	/** The Constant ERRO_SALVAR. */
	protected static final String ERRO_SALVAR = "Erro de Transação ao Salvar: ";
	
	/** The Constant ERRO_DELETE. */
	protected static final String ERRO_DELETE = "Erro de Transação ao Excluir: ";
	
	/**
	 * Pega o the pagina desktop.
	 *
	 * @return o the pagina desktop
	 */
	public String getPaginaDesktop(){
		return "/";
	}

	/**
	 * Gerar mensagem erro.
	 *
	 * @param mensagem the mensagem
	 */
	/*
	 * Gerar mensagem erro.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemErro(String mensagem) {
		//context.addMessage(null,
			//	new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensagem));
	}

	/**
	 * Gerar mensagem erro.
	 *
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemErro(Exception e, String mensagem) {
		//context.addMessage(null,
			//	new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ExcecaoUtil.getErros(log, e, mensagem)));
	}

	/**
	 * Gerar mensagem informativa.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemInformativa(String mensagem) {
		//context.addMessage(null,
			//	new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensagem));
	}

	/**
	 * Gerar mensagem aviso.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemAviso(String mensagem) {
		//context.addMessage(null,
			//	new FacesMessage(FacesMessage.SEVERITY_WARN, null, mensagem));
	}

	/**
	 * Gerar mensagem erro.
	 *
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 * @param clientId
	 *            the client id
	 */
	public void gerarMensagemErro(Exception e, String mensagem, String clientId) {
		//context.addMessage(clientId,
			//	new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ExcecaoUtil.getErros(log, e, mensagem)));
	}

	/**
	 * Gerar mensagem informativa.
	 *
	 * @param mensagem
	 *            the mensagem
	 * @param clientId
	 *            the client id
	 */
	public void gerarMensagemInformativa(String mensagem, String clientId) {
		//context.addMessage(clientId,
			//	new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensagem));
	}

	/**
	 * Gerar mensagem aviso.
	 *
	 * @param mensagem
	 *            the mensagem
	 * @param clientId
	 *            the client id
	 */
	public void gerarMensagemAviso(String mensagem, String clientId) {
		//context.addMessage(clientId,
				//new FacesMessage(FacesMessage.SEVERITY_WARN, null, mensagem));
	}
	
	/**
	 * Adiciona o message info dialog.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public static void addMessageInfoDialog(String mensagem) {
		//RequestContext.getCurrentInstance()
			//	.showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", mensagem));
	}

	/**
	 * Adiciona o message alerta dialog.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public static void addMessageAlertaDialog(String mensagem) {
		//RequestContext.getCurrentInstance()
			//	.showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", mensagem));
	}

	/**
	 * Adiciona o message erro dialog.
	 *
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 */
	public static void addMessageErroDialog(Exception e, String mensagem) {
		//RequestContext.getCurrentInstance()
			//	.showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", ExcecaoUtil.getErros(LogManager.getLogger(), e, mensagem)));
	}
	
	/**
	 * Gets the sessao.
	 *
	 * @return the sessao
	 */
	public HttpSession getSessao() {
		//HttpServletRequest hsr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		//return hsr.getSession();
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession();
		
	}
	
	/**
	 * Gets the atributos sessao.
	 *
	 * @return the atributos sessao
	 */
	public Map<String, String> getAtributosSessao() {
		HttpSession sessao = getSessao();
		HashMap<String, String> mapa = new HashMap<String, String>();
		String atributo, valor;
		for (Enumeration<String> item = sessao.getAttributeNames(); item.hasMoreElements();) {
			atributo = item.nextElement();
			valor = sessao.getAttribute(atributo).toString();
			mapa.put(atributo, valor);
		}
		return mapa;
	}
	
	/**
	 * Log atributos sessao.
	 */
	public void logAtributosSessao() {
		log.info("Sessão: [");
		for (Entry<String, String> item : getAtributosSessao().entrySet()) {
			log.info("\n" + item.getKey() + " = " + item.getValue());
		}
		log.info("]");
	}
	
	/**
	 * Pega o the usuario autenticado.
	 *
	 * @return o the usuario autenticado
	 */
	public UsuarioAutenticadoVO getUsuarioAutenticado() {		
		return (UsuarioAutenticadoVO) getSessao().getAttribute("usuarioAutenticado");
	}
	
	/**
	 * Sets the usuario autenticado.
	 *
	 * @param usu
	 *            the new usuario autenticado
	 */
	public void setUsuarioAutenticado(UsuarioAutenticadoVO usu){
		getSessao().setAttribute("usuarioAutenticado", usu);
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
	
	public MenuVO getMenuAtual(){
		String idMenu = getIdMenu();
		if (!idMenu.isEmpty())
			return getUsuarioAutenticado().getMenu(idMenu);
		else 
			return null;
	}	
}
