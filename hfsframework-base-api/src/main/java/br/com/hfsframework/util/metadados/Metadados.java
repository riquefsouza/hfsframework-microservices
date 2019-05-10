/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.metadados;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Metadados.
 */
public class Metadados implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The versao driver JDBC. */
	private String versaoDriverJDBC;

	/** The versao banco de dados. */
	private String versaoBancoDeDados;

	/** The suporta select for update. */
	private boolean suportaSelectForUpdate;

	/** The suporta transacoes. */
	private boolean suportaTransacoes;

	/** The objetos. */
	private List<MetadadosObjeto> objetos;

	/**
	 * Instantiates a new metadados.
	 */
	public Metadados() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new metadados.
	 *
	 * @param versaoDriverJDBC the versao driver JDBC
	 * @param versaoBancoDeDados the versao banco de dados
	 * @param suportaSelectForUpdate the suporta select for update
	 * @param suportaTransacoes the suporta transacoes
	 * @param objetos the objetos
	 */
	public Metadados(String versaoDriverJDBC, String versaoBancoDeDados, boolean suportaSelectForUpdate,
			boolean suportaTransacoes, List<MetadadosObjeto> objetos) {
		super();
		this.versaoDriverJDBC = versaoDriverJDBC;
		this.versaoBancoDeDados = versaoBancoDeDados;
		this.suportaSelectForUpdate = suportaSelectForUpdate;
		this.suportaTransacoes = suportaTransacoes;
		this.objetos = objetos;
	}

	/**
	 * Gets the versao driver JDBC.
	 *
	 * @return the versao driver JDBC
	 */
	public String getVersaoDriverJDBC() {
		return versaoDriverJDBC;
	}

	/**
	 * Sets the versao driver JDBC.
	 *
	 * @param versaoDriverJDBC the new versao driver JDBC
	 */
	public void setVersaoDriverJDBC(String versaoDriverJDBC) {
		this.versaoDriverJDBC = versaoDriverJDBC;
	}

	/**
	 * Gets the versao banco de dados.
	 *
	 * @return the versao banco de dados
	 */
	public String getVersaoBancoDeDados() {
		return versaoBancoDeDados;
	}

	/**
	 * Sets the versao banco de dados.
	 *
	 * @param versaoBancoDeDados the new versao banco de dados
	 */
	public void setVersaoBancoDeDados(String versaoBancoDeDados) {
		this.versaoBancoDeDados = versaoBancoDeDados;
	}

	/**
	 * Checks if is suporta select for update.
	 *
	 * @return true, if is suporta select for update
	 */
	public boolean isSuportaSelectForUpdate() {
		return suportaSelectForUpdate;
	}

	/**
	 * Sets the suporta select for update.
	 *
	 * @param suportaSelectForUpdate the new suporta select for update
	 */
	public void setSuportaSelectForUpdate(boolean suportaSelectForUpdate) {
		this.suportaSelectForUpdate = suportaSelectForUpdate;
	}

	/**
	 * Checks if is suporta transacoes.
	 *
	 * @return true, if is suporta transacoes
	 */
	public boolean isSuportaTransacoes() {
		return suportaTransacoes;
	}

	/**
	 * Sets the suporta transacoes.
	 *
	 * @param suportaTransacoes the new suporta transacoes
	 */
	public void setSuportaTransacoes(boolean suportaTransacoes) {
		this.suportaTransacoes = suportaTransacoes;
	}

	/**
	 * Gets the objetos.
	 *
	 * @return the objetos
	 */
	public List<MetadadosObjeto> getObjetos() {
		return objetos;
	}

	/**
	 * Sets the objetos.
	 *
	 * @param objetos the new objetos
	 */
	public void setObjetos(List<MetadadosObjeto> objetos) {
		this.objetos = objetos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Metadados [\nversaoDriverJDBC=" + versaoDriverJDBC + ", \nversaoBancoDeDados=" + versaoBancoDeDados
				+ ", \nsuportaSelectForUpdate=" + suportaSelectForUpdate + ", \nsuportaTransacoes=" + suportaTransacoes
				+ ", \nobjetos=" + objetos + "]";
	}

}
