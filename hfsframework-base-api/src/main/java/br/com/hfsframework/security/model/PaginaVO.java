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
 * The Class PaginaVO.
 */
@Component
public class PaginaVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The descricao. */
	private String descricao;

	/** The url. */
	private String url;

	/** The perfils. */
	private List<PerfilVO> perfils;

	/**
	 * Instantiates a new pagina VO.
	 */
	public PaginaVO() {
		this.perfils = new ArrayList<PerfilVO>();
		limpar();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.descricao = null;
		this.url = null;
		this.perfils.clear();
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
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param descricao
	 *            the new descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url
	 *            the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the perfils.
	 *
	 * @return the perfils
	 */
	public List<PerfilVO> getPerfils() {
		return this.perfils;
	}

	/**
	 * Sets the perfils.
	 *
	 * @param perfils
	 *            the new perfils
	 */
	public void setPerfils(List<PerfilVO> perfils) {
		this.perfils = perfils;
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
		PaginaVO other = (PaginaVO) obj;
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
		return this.url;
	}

	/**
	 * Gets the perfis pagina.
	 *
	 * @return the perfis pagina
	 */
	public String getPerfisPagina() {
		String ret = "";
		for (PerfilVO item : getPerfils()) {
			ret = ret.concat(item.getDescricao()).concat(", ");
		}
		if (ret != "") {
			ret = ret.substring(0, ret.length() - 2);
		}
		return ret;
	}
	

}
