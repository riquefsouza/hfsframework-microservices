/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuVO.
 */
@Component
public class MenuVO implements Serializable, Comparable<MenuVO> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The description. */
	private String description;

	/** The order. */
	private Integer order;

	/** The id page. */
	private Long idPage;

	/** The page. */
	private PageVO page;

	private MenuVO menuParent;

	/** The sub menus. */
	private List<MenuVO> subMenus;

	/**
	 * Instantiates a new menu VO.
	 */
	public MenuVO() {
		this.subMenus = new ArrayList<MenuVO>();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.description = null;
		this.order = null;
		this.idPage = null;
		this.page = new PageVO();
		this.menuParent = null;
		this.subMenus.clear();
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Pega o the description.
	 *
	 * @return o the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Atribui o the description.
	 *
	 * @param description
	 *            o novo the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Pega o the order.
	 *
	 * @return o the order
	 */
	public Integer getOrder() {
		return this.order;
	}

	/**
	 * Atribui o the order.
	 *
	 * @param order
	 *            o novo the order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * Pega o the adm page.
	 *
	 * @return o the adm page
	 */
	public PageVO getPage() {
		return this.page;
	}

	/**
	 * Atribui o the adm page.
	 *
	 * @param page
	 *            o novo the adm page
	 */
	public void setPage(PageVO page) {
		this.page = page;
	}

	/**
	 * Pega o the adm menu.
	 *
	 * @return o the adm menu
	 */
	public MenuVO getMenuParent() {
		return this.menuParent;
	}

	/**
	 * Atribui o the adm menu.
	 *
	 * @param menuParent
	 *            o novo the adm menu
	 */
	public void setMenuParent(MenuVO menuParent) {
		this.menuParent = menuParent;
	}

	/**
	 * Pega o the adm menus.
	 *
	 * @return o the adm menus
	 */
	public List<MenuVO> getSubMenus() {
		if (this.subMenus!=null && !this.subMenus.isEmpty()){
			Collections.sort(this.subMenus, new Comparator<MenuVO>() {
				@Override
				public int compare(MenuVO o1, MenuVO o2) {
					return o1.getOrder().compareTo(o2.getOrder());
				}
			});
		}
		return this.subMenus;
	}

	/**
	 * Atribui o the adm menus.
	 *
	 * @param subMenus
	 *            o novo the adm menus
	 */
	public void setSubMenus(List<MenuVO> subMenus) {
		this.subMenus = subMenus;
	}

	/**
	 * Adiciona o adm sub menus.
	 *
	 * @param subMenus
	 *            the adm sub menus
	 * @return the adm menu
	 */
	public MenuVO addSubMenus(MenuVO subMenus) {
		getSubMenus().add(subMenus);
		subMenus.setMenuParent(this);

		return subMenus;
	}

	/**
	 * Remove o adm sub menus.
	 *
	 * @param subMenus
	 *            the adm sub menus
	 * @return the adm menu
	 */
	public MenuVO removeSubMenus(MenuVO subMenus) {
		getSubMenus().remove(subMenus);
		subMenus.setMenuParent(null);

		return subMenus;
	}
		
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuParent == null) ? 0 : menuParent.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		MenuVO other = (MenuVO) obj;
		if (menuParent == null) {
			if (other.menuParent != null)
				return false;
		} else if (!menuParent.equals(other.menuParent))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Checks if is sub menu.
	 *
	 * @return true, if is sub menu
	 */
	public boolean isSubMenu() {
		return getPage() == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MenuVO m) {
		return getDescription().compareTo(m.getDescription());
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.page != null ? this.page.getUrl() : null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.description;
	}

	/**
	 * Pega o the id page.
	 *
	 * @return o the id page
	 */
	public Long getIdPage() {
		return idPage;
	}

	/**
	 * Atribui o the id page.
	 *
	 * @param idPage
	 *            o novo the id page
	 */
	public void setIdPage(Long idPage) {
		this.idPage = idPage;
	}

}
