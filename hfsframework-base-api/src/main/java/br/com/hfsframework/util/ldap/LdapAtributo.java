/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.ldap;

import java.io.Serializable;

/**
 * The Class LdapAtributo.
 */
public class LdapAtributo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private String id;

	/** The valor. */
	private String valor;

	/**
	 * Instantiates a new ldap atributo.
	 */
	public LdapAtributo() {
		super();
	}

	/**
	 * Instantiates a new ldap atributo.
	 *
	 * @param id
	 *            the id
	 * @param valor
	 *            the valor
	 */
	public LdapAtributo(String id, String valor) {
		super();
		this.id = id;
		this.valor = valor;
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Pega o the valor.
	 *
	 * @return o the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Atribui o the valor.
	 *
	 * @param valor
	 *            o novo the valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
}
