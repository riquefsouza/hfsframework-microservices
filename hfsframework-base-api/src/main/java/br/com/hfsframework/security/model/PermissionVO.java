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

	/** The profile. */
	private ProfileVO profile;
	
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
		this.profile = new ProfileVO();
		this.pages.clear();
	}

	/**
	 * Pega o the profile.
	 *
	 * @return o the profile
	 */
	public ProfileVO getProfile() {
		return profile;
	}

	/**
	 * Atribui o the profile.
	 *
	 * @param profile
	 *            o novo the profile
	 */
	public void setProfile(ProfileVO profile) {
		this.profile = profile;
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
		return "PermissaoVO [profile=" + profile + ", pages=" + pages + "]";
	}



}
