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

@Component
public class ProfileVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The administrator. */
	private Boolean administrator;

	/** The description. */
	private String description;

	/** The geral. */
	private Boolean geral;

	/** The pages. */
	private List<PageVO> pages;
	
	/** The users. */
	private List<UserVO> users;
		
	public ProfileVO() {
		this.pages = new ArrayList<PageVO>();
		this.users = new ArrayList<UserVO>();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.administrator = null;
		this.description = null;
		this.geral = null;
		this.pages.clear();
		this.users.clear();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the administrator.
	 *
	 * @return the administrator
	 */
	public Boolean getAdministrator() {
		return this.administrator;
	}

	/**
	 * Sets the administrator.
	 *
	 * @param administrator
	 *            the new administrator
	 */
	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the geral.
	 *
	 * @return the geral
	 */
	public Boolean getGeral() {
		return this.geral;
	}

	/**
	 * Sets the geral.
	 *
	 * @param geral
	 *            the new geral
	 */
	public void setGeral(Boolean geral) {
		this.geral = geral;
	}

	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
	public List<PageVO> getPages() {
		return pages;
	}

	/**
	 * Sets the pages.
	 *
	 * @param pages
	 *            the new pages
	 */
	public void setPages(List<PageVO> pages) {
		this.pages = pages;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<UserVO> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users
	 *            the new users
	 */
	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ProfileVO other = (ProfileVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return description;
	}
	
}
