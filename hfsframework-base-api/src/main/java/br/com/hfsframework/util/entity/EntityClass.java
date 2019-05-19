/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.entity;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EntidadeClasse.
 */
public class EntityClass {

	/** The pacote. */
	private String pacote;
	
	/** The nome. */
	private String nome;
	
	/** The campos. */
	private List<EntityField> campos;

	/**
	 * Instantiates a new entidade classe.
	 *
	 * @param pacote
	 *            the pacote
	 * @param nome
	 *            the nome
	 * @param campos
	 *            the campos
	 */
	public EntityClass(String pacote, String nome, List<EntityField> campos) {
		super();
		this.pacote = pacote;
		this.nome = nome;
		this.campos = campos;
	}

	/**
	 * Gets the pacote.
	 *
	 * @return the pacote
	 */
	public String getPacote() {
		return pacote;
	}

	/**
	 * Sets the pacote.
	 *
	 * @param pacote
	 *            the new pacote
	 */
	public void setPacote(String pacote) {
		this.pacote = pacote;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome
	 *            the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the campos.
	 *
	 * @return the campos
	 */
	public List<EntityField> getCampos() {
		return campos;
	}

	/**
	 * Sets the campos.
	 *
	 * @param campos
	 *            the new campos
	 */
	public void setCampos(List<EntityField> campos) {
		this.campos = campos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Classe [pacote=" + pacote + ", nome=" + nome + ", campos=" + campos + "]";
	}
	
}
