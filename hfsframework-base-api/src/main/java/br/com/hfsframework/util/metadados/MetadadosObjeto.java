/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.metadados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Metadados.
 */
public class MetadadosObjeto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The esquema. */
	private String esquema;

	/** The objeto. */
	private String objeto;

	/** The colunas. */
	private List<MetadadosColuna> colunas;
	
	/** The nome colunas PK. */
	private List<String> nomeColunasPK;	

	/** The nome colunas. */
	private List<String> nomeColunas;	
	
	/**
	 * Instantiates a new metadados objeto.
	 */
	public MetadadosObjeto() {
		super();
		this.colunas = new ArrayList<MetadadosColuna>();
	}

	/**
	 * Instantiates a new metadados objeto.
	 *
	 * @param esquema
	 *            the esquema
	 * @param objeto
	 *            the objeto
	 * @param colunas
	 *            the colunas
	 */
	public MetadadosObjeto(String esquema, String objeto, List<MetadadosColuna> colunas) {
		super();
		this.esquema = esquema;
		this.objeto = objeto;
		this.colunas = colunas;
	}

	/**
	 * Gets the esquema.
	 *
	 * @return the esquema
	 */
	public String getEsquema() {
		return esquema;
	}

	/**
	 * Sets the esquema.
	 *
	 * @param esquema
	 *            the new esquema
	 */
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}

	/**
	 * Gets the objeto.
	 *
	 * @return the objeto
	 */
	public String getObjeto() {
		return objeto;
	}

	/**
	 * Sets the objeto.
	 *
	 * @param objeto
	 *            the new objeto
	 */
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	/**
	 * Gets the colunas.
	 *
	 * @return the colunas
	 */
	public List<MetadadosColuna> getColunas() {
		return colunas;
	}

	/**
	 * Sets the colunas.
	 *
	 * @param colunas
	 *            the new colunas
	 */
	public void setColunas(List<MetadadosColuna> colunas) {
		this.colunas = colunas;
	}

	/**
	 * Pega o the nome colunas PK.
	 *
	 * @return o the nome colunas PK
	 */
	public List<String> getNomeColunasPK() {
		return nomeColunasPK;
	}

	/**
	 * Atribui o the nome colunas PK.
	 *
	 * @param nomeColunasPK
	 *            o novo the nome colunas PK
	 */
	public void setNomeColunasPK(List<String> nomeColunasPK) {
		this.nomeColunasPK = nomeColunasPK;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n" + esquema + "." + objeto + "(" + colunas + ");";
	}

	/**
	 * Gets the nome colunas.
	 *
	 * @return the nome colunas
	 */
	public List<String> getNomeColunas() {
		return nomeColunas;
	}

	/**
	 * Sets the nome colunas.
	 *
	 * @param nomeColunas the new nome colunas
	 */
	public void setNomeColunas(List<String> nomeColunas) {
		this.nomeColunas = nomeColunas;
	}

}
