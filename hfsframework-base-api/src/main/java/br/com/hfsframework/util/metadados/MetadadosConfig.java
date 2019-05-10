/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.metadados;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class MetadadosConfig.
 */
public class MetadadosConfig implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The conexao URL. */
	private String conexaoURL;

	/** The driver classe. */
	private String driverClasse;

	/** The usuario. */
	private String usuario;

	/** The senha. */
	private String senha;

	/**
	 * Instantiates a new metadados config.
	 */
	public MetadadosConfig() {
		super();
	}

	/**
	 * Instantiates a new metadados config.
	 *
	 * @param conexaoURL the conexao URL
	 * @param driverClasse the driver classe
	 * @param usuario the usuario
	 * @param senha the senha
	 */
	public MetadadosConfig(String conexaoURL, String driverClasse, String usuario, String senha) {
		super();
		this.conexaoURL = conexaoURL;
		this.driverClasse = driverClasse;
		this.usuario = usuario;
		this.senha = senha;
	}

	/**
	 * Hfsbanco DS.
	 *
	 * @return the metadados config
	 */
	public static MetadadosConfig hfsbancoDS() {
		String conexaoURL = "jdbc:postgresql://localhost:5432/hfsbanco";
		String driverClasse = "org.postgresql.Driver";
		String usuario = "postgres";
		String senha = "abcd1234";

		MetadadosConfig cfg = new MetadadosConfig(conexaoURL, driverClasse, usuario, senha);
		return cfg;
	}

	/**
	 * Gets the conexao URL.
	 *
	 * @return the conexao URL
	 */
	public String getConexaoURL() {
		return conexaoURL;
	}

	/**
	 * Sets the conexao URL.
	 *
	 * @param conexaoURL the new conexao URL
	 */
	public void setConexaoURL(String conexaoURL) {
		this.conexaoURL = conexaoURL;
	}

	/**
	 * Gets the driver classe.
	 *
	 * @return the driver classe
	 */
	public String getDriverClasse() {
		return driverClasse;
	}

	/**
	 * Sets the driver classe.
	 *
	 * @param driverClasse the new driver classe
	 */
	public void setDriverClasse(String driverClasse) {
		this.driverClasse = driverClasse;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the senha.
	 *
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Sets the senha.
	 *
	 * @param senha the new senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MetadadosConfig [conexaoURL=" + conexaoURL + ", driverClasse=" + driverClasse + ", usuario=" + usuario
				+ ", senha=" + senha + "]";
	}

}
