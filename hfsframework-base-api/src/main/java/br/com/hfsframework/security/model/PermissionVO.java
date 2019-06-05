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

// TODO: Auto-generated Javadoc
/**
 * The Class PermissaoVO.
 */
@Component
public class PermissionVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The perfil. */
	private ProfileVO perfil;
	
	/** The pages. */
	private List<PageVO> pages;

	/**
	 * Instantiates a new permissao VO.
	 */
	public PermissionVO() {
		super();
		this.pages = new ArrayList<PageVO>();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.perfil = new ProfileVO();
		this.pages.clear();
	}

	/**
	 * Pega o the perfil.
	 *
	 * @return o the perfil
	 */
	public ProfileVO getPerfil() {
		return perfil;
	}

	/**
	 * Atribui o the perfil.
	 *
	 * @param perfil
	 *            o novo the perfil
	 */
	public void setPerfil(ProfileVO perfil) {
		this.perfil = perfil;
	}

	/**
	 * Pega o the pages.
	 *
	 * @return o the pages
	 */
	public List<PageVO> getPages() {
		return pages;
	}

	/**
	 * Atribui o the pages.
	 *
	 * @param pages
	 *            o novo the pages
	 */
	public void setPages(List<PageVO> pages) {
		this.pages = pages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PermissaoVO [perfil=" + perfil + ", pages=" + pages + "]";
	}



}
