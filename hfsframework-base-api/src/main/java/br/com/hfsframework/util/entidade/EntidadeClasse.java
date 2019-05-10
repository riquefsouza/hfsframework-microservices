/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.entidade;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EntidadeClasse.
 */
public class EntidadeClasse {

	/** The pacote. */
	private String pacote;
	
	/** The nome. */
	private String nome;
	
	/** The campos. */
	private List<EntidadeCampo> campos;

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
	public EntidadeClasse(String pacote, String nome, List<EntidadeCampo> campos) {
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
	public List<EntidadeCampo> getCampos() {
		return campos;
	}

	/**
	 * Sets the campos.
	 *
	 * @param campos
	 *            the new campos
	 */
	public void setCampos(List<EntidadeCampo> campos) {
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
