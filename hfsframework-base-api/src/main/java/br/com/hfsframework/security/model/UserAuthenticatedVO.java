/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * The Class UsuarioAutenticadoVO.
 */

@Component
public class UserAuthenticatedVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user name. */
	private String userName;

	/** The display name. */
	private String displayName;
	
	/** The email. */
	private String email;
	
	/** The lista permissao. */
	private List<PermissionVO> listaPermissao;

	/** The lista menus. */
	private List<MenuVO> listaMenus;
	
	/** The lista admin menus. */
	private List<MenuVO> listaAdminMenus;
	
	/** The usuario. */
	private UserVO usuario;
	
	private boolean modoTeste;
	
	/**
	 * Instantiates a new usuario.
	 */
	public UserAuthenticatedVO() {
		super();
		
		this.listaPermissao = new ArrayList<PermissionVO>();
		this.usuario = new UserVO();
		this.listaMenus = new ArrayList<MenuVO>();
		this.listaAdminMenus = new ArrayList<MenuVO>();
				
		limpar();
		
		this.modoTeste = false;
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.userName = "";
		this.displayName = "";
		this.email = "";
		this.listaPermissao.clear();
		this.listaMenus.clear();
		this.listaAdminMenus.clear();		
		this.usuario.limpar();
	}
		
	/**
	 * Instantiates a new usuario autenticado VO.
	 *
	 * @param userName
	 *            the user name
	 */
	public UserAuthenticatedVO(String userName) {
		super();
		this.userName = userName;
	}

	/**
	 * Pega o the user name.
	 *
	 * @return o the user name
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Atribui o the user name.
	 *
	 * @param userName
	 *            o novo the user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAuthenticatedVO other = (UserAuthenticatedVO) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	/**
	 * Pega o the display name.
	 *
	 * @return o the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Atribui o the display name.
	 *
	 * @param displayName
	 *            o novo the display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Pega o the email.
	 *
	 * @return o the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Atribui o the email.
	 *
	 * @param email
	 *            o novo the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Pega o the lista permissao.
	 *
	 * @return o the lista permissao
	 */
	public List<PermissionVO> getListaPermissao() {
		return listaPermissao;
	}

	/**
	 * Atribui o the lista permissao.
	 *
	 * @param listaPermissao
	 *            o novo the lista permissao
	 */
	public void setListaPermissao(List<PermissionVO> listaPermissao) {
		this.listaPermissao = listaPermissao;
	}

	/**
	 * Pega o the usuario.
	 *
	 * @return o the usuario
	 */
	public UserVO getUsuario() {
		return usuario;
	}

	/**
	 * Atribui o the usuario.
	 *
	 * @param usuario
	 *            o novo the usuario
	 */
	public void setUsuario(UserVO usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the perfil.
	 *
	 * @param idPerfil
	 *            the id perfil
	 * @return the perfil
	 */
	public RoleVO getPerfil(Long idPerfil){
		RoleVO admPerfil = null;
		for (PermissionVO permissaoVO : listaPermissao) {
			if (permissaoVO.getPerfil().getId() == idPerfil){
				admPerfil = permissaoVO.getPerfil(); 
				break;
			}
		}
		return admPerfil;
	}

	/**
	 * Checks if is perfil.
	 *
	 * @param idPerfil
	 *            the id perfil
	 * @return true, if is perfil
	 */
	public boolean isPerfil(Long idPerfil){
		return (getPerfil(idPerfil)!=null);
	}

	/**
	 * Existe perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the perfil
	 */
	public RoleVO getPerfil(String perfil){
		RoleVO admPerfil = null;
		for (PermissionVO permissaoVO : listaPermissao) {
			if (permissaoVO.getPerfil().getDescricao().equalsIgnoreCase(perfil)){
				admPerfil = permissaoVO.getPerfil(); 
				break;
			}
		}
		return admPerfil;
	}

	/**
	 * Checks if is perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return true, if is perfil
	 */
	public boolean isPerfil(String perfil){
		return (getPerfil(perfil)!=null);
	}
	
	/**
	 * Gets the perfil geral.
	 *
	 * @return the perfil geral
	 */
	public RoleVO getPerfilGeral(){
		RoleVO admPerfil = null;
		for (PermissionVO permissaoVO : listaPermissao) {
			if (permissaoVO.getPerfil().getGeral()){
				admPerfil = permissaoVO.getPerfil(); 
				break;
			}
		}
		return admPerfil;
	}

	/**
	 * Gets the perfil administrador.
	 *
	 * @return the perfil administrador
	 */
	public RoleVO getPerfilAdministrador(){
		RoleVO admPerfil = null;
		for (PermissionVO permissaoVO : listaPermissao) {
			if (permissaoVO.getPerfil().getAdministrador()){
				admPerfil = permissaoVO.getPerfil(); 
				break;
			}
		}
		return admPerfil;
	}
	
	/**
	 * Checks if is geral.
	 *
	 * @return true, if is geral
	 */
	public boolean isGeral(){
        RoleVO perfil = this.getPerfilGeral();
        if (perfil!=null){
        	return perfil.getGeral();
        }
        return false;
	}

	/**
	 * Checks if is administrador.
	 *
	 * @return true, if is administrador
	 */
	public boolean isAdministrador(){
        RoleVO perfil = this.getPerfilAdministrador();
        if (perfil!=null){
        	return perfil.getAdministrador();
        }
        return false;
	}

	/**
	 * Pega o the lista menus.
	 *
	 * @return o the lista menus
	 */
	public List<MenuVO> getListaMenus() {
		return listaMenus;
	}

	/**
	 * Atribui o the lista menus.
	 *
	 * @param listaMenus
	 *            o novo the lista menus
	 */
	public void setListaMenus(List<MenuVO> listaMenus) {
		this.listaMenus = listaMenus;
	}

	/**
	 * Pega o the lista admin menus.
	 *
	 * @return o the lista admin menus
	 */
	public List<MenuVO> getListaAdminMenus() {
		return listaAdminMenus;
	}

	/**
	 * Atribui o the lista admin menus.
	 *
	 * @param listaAdminMenus
	 *            o novo the lista admin menus
	 */
	public void setListaAdminMenus(List<MenuVO> listaAdminMenus) {
		this.listaAdminMenus = listaAdminMenus;
	}

	/**
	 * Gets the pagina by menu.
	 *
	 * @param idMenu
	 *            the id menu
	 * @return the pagina by menu
	 */
	public PageVO getPaginaByMenu(Long idMenu) {
		PageVO admPagina = null;
		
		if (listaMenus!= null && !listaMenus.isEmpty()){
			for (MenuVO admMenu : listaMenus) {
				admPagina = admMenu.getPagina();
				break;
			}			
		}
		
		if (listaAdminMenus!= null && !listaAdminMenus.isEmpty()){
			for (MenuVO admMenu : listaAdminMenus) {
				admPagina = admMenu.getPagina();
				break;
			}			
		}
		
		return admPagina;
	}
	
	/**
	 * Checks if is possui permissao.
	 *
	 * @param url
	 *            the url
	 * @param tela
	 *            the tela
	 * @return true, if is possui permissao
	 */
	public boolean isPossuiPermissao(String url, String tela) {
		//AplicacaoUtil aplicacaoUtil = new AplicacaoUtil();
		
		//if (aplicacaoUtil.isDebugMode()) {
			//return true;
		//}
		if (url == null) {
			return false;
		}
		int pos = url.indexOf("private");
		url = pos > -1 ? url.substring(pos + 8, url.length()) : url;

		if (url.equals(tela)) {
			return true;
		}

		for (PermissionVO permissao : this.getListaPermissao()) {
			for (PageVO admPagina : permissao.getPaginas()) {
				if (admPagina.getUrl().equals(url)) {
					return true;
				}
			}
		}

		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsuarioAutenticadoVO [userName=" + userName + ", displayName=" + displayName + ", email=" + email
				+ ", listaPermissao=" + listaPermissao + ", listaMenus=" + listaMenus + ", listaAdminMenus="
				+ listaAdminMenus + ", usuario=" + usuario + "]";
	}

	public MenuVO getMenu(String sidMenu){
		MenuVO menu = null;
		Long idMenu = Long.valueOf(sidMenu);
		
		menu = listaMenus.stream()
			.flatMap(pai -> pai.getSubMenus().stream())			
			.filter(submenu -> submenu.getId().equals(idMenu))
			.findFirst()
			.orElse(null);

		if (menu == null) { 
			menu = listaAdminMenus.stream()
					.flatMap(pai -> pai.getSubMenus().stream())			
					.filter(submenu -> submenu.getId().equals(idMenu))
					.findFirst()
					.orElse(null);			
		}
		
		return menu;
	}

	public boolean isModoTeste() {
		return modoTeste;
	}

	public void setModoTeste(boolean modoTeste) {
		this.modoTeste = modoTeste;
	}
		
}
