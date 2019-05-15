/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.ldap;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class LdapBundle.
 */
@Component
public class LdapBundle implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ldap S porta. */
	private int ldapSPorta;
	
	/** The ldap S protocolo. */
	private String ldapSProtocolo;
	
	/** The ldap porta. */
	private int ldapPorta;
	
	/** The ldap tipo conexao. */
	private String ldapTipoConexao;
	
	/** The ldap filter. */
	private String ldapFilter;
	
	/** The ldap dn inativos. */
	private String ldapDnInativos;
	
	/** The ldap atributos. */
	private String ldapAtributos;
	
	/** The ldap atributo cod funcional. */
	private String ldapAtributoCodFuncional;
	
	/** The ldap server. */
	private String ldapServer;
	
	/** The ldap user senha. */
	private String ldapUserSenha;
	
	/** The ldap base DN. */
	private String ldapBaseDN;
	
	/** The ldap user DN. */
	private String ldapUserDN;
	
	/** The bundle. */
	private ResourceBundle bundle;

	/**
	 * Instantiates a new ldap config.
	 */
	public LdapBundle() {
		super();

		Locale locale = new Locale("pt", "BR");
		bundle = ResourceBundle.getBundle("ldap", locale);
	}

	/**
	 * Instantiates a new ldap config.
	 *
	 * @param bundle
	 *            the bundle
	 */
	public LdapBundle(ResourceBundle bundle) {
		this.bundle = bundle; 
	}
	
	/**
	 * Pega o the ldap S porta.
	 *
	 * @return o the ldap S porta
	 */
	public int getLdapSPorta() {
		if (bundle == null)
			return ldapSPorta;
		else	
			return Integer.parseInt(bundle.getString("ldapSPorta"));
	}

	/**
	 * Pega o the ldap S protocolo.
	 *
	 * @return o the ldap S protocolo
	 */
	public String getLdapSProtocolo() {
		if (bundle == null)
			return ldapSProtocolo;
		else	
			return bundle.getString("ldapSProtocolo");
	}

	/**
	 * Pega o the ldap porta.
	 *
	 * @return o the ldap porta
	 */
	public int getLdapPorta() {
		if (bundle == null)
			return ldapPorta;
		else	
			return Integer.parseInt(bundle.getString("ldapPorta"));
	}

	/**
	 * Pega o the ldap tipo conexao.
	 *
	 * @return o the ldap tipo conexao
	 */
	public String getLdapTipoConexao() {
		if (bundle == null)
			return ldapTipoConexao;
		else	
			return bundle.getString("ldapTipoConexao");
	}

	/**
	 * Pega o the ldap filter.
	 *
	 * @return o the ldap filter
	 */
	public String getLdapFilter() {
		if (bundle == null)
			return ldapFilter;
		else	
			return bundle.getString("ldapFilter");
	}

	/**
	 * Pega o the ldap dn inativos.
	 *
	 * @return o the ldap dn inativos
	 */
	public String getLdapDnInativos() {
		if (bundle == null)
			return ldapDnInativos;
		else	
			return bundle.getString("ldapDnInativos");
	}

	/**
	 * Pega o the ldap atributo cod funcional.
	 *
	 * @return o the ldap atributo cod funcional
	 */
	public String getLdapAtributoCodFuncional() {
		if (bundle == null)
			return ldapAtributoCodFuncional;
		else	
			return bundle.getString("ldapAtributoCodFuncional");
	}

	/**
	 * Pega o the ldap server.
	 *
	 * @return o the ldap server
	 */
	public String getLdapServer() {
		if (bundle == null)
			return ldapServer;
		else	
			return bundle.getString("ldapServer");
	}

	/**
	 * Pega o the ldap user senha.
	 *
	 * @return o the ldap user senha
	 */
	public String getLdapUserSenha() {
		if (bundle == null)
			return ldapUserSenha;
		else	
			return bundle.getString("ldapUserSenha");
	}

	/**
	 * Pega o the ldap base DN.
	 *
	 * @return o the ldap base DN
	 */
	public String getLdapBaseDN() {
		if (bundle == null)
			return ldapBaseDN;
		else	
			return bundle.getString("ldapBaseDN");
	}

	/**
	 * Pega o the ldap user DN.
	 *
	 * @return o the ldap user DN
	 */
	public String getLdapUserDN() {
		if (bundle == null)
			return ldapUserDN;
		else	
			return bundle.getString("ldapUserDN");
	}

	/**
	 * Pega o the ldap atributos.
	 *
	 * @return o the ldap atributos
	 */
	public String getLdapAtributos() {
		if (bundle == null)
			return ldapAtributos;
		else	
			return bundle.getString("ldapAtributos");
	}

	/**
	 * Atribui o the ldap S porta.
	 *
	 * @param ldapSPorta
	 *            o novo the ldap S porta
	 */
	public void setLdapSPorta(int ldapSPorta) {
		this.ldapSPorta = ldapSPorta;
	}

	/**
	 * Atribui o the ldap S protocolo.
	 *
	 * @param ldapSProtocolo
	 *            o novo the ldap S protocolo
	 */
	public void setLdapSProtocolo(String ldapSProtocolo) {
		this.ldapSProtocolo = ldapSProtocolo;
	}

	/**
	 * Atribui o the ldap porta.
	 *
	 * @param ldapPorta
	 *            o novo the ldap porta
	 */
	public void setLdapPorta(int ldapPorta) {
		this.ldapPorta = ldapPorta;
	}

	/**
	 * Atribui o the ldap tipo conexao.
	 *
	 * @param ldapTipoConexao
	 *            o novo the ldap tipo conexao
	 */
	public void setLdapTipoConexao(String ldapTipoConexao) {
		this.ldapTipoConexao = ldapTipoConexao;
	}

	/**
	 * Atribui o the ldap filter.
	 *
	 * @param ldapFilter
	 *            o novo the ldap filter
	 */
	public void setLdapFilter(String ldapFilter) {
		this.ldapFilter = ldapFilter;
	}

	/**
	 * Atribui o the ldap dn inativos.
	 *
	 * @param ldapDnInativos
	 *            o novo the ldap dn inativos
	 */
	public void setLdapDnInativos(String ldapDnInativos) {
		this.ldapDnInativos = ldapDnInativos;
	}

	/**
	 * Atribui o the ldap atributos.
	 *
	 * @param ldapAtributos
	 *            o novo the ldap atributos
	 */
	public void setLdapAtributos(String ldapAtributos) {
		this.ldapAtributos = ldapAtributos;
	}

	/**
	 * Atribui o the ldap atributo cod funcional.
	 *
	 * @param ldapAtributoCodFuncional
	 *            o novo the ldap atributo cod funcional
	 */
	public void setLdapAtributoCodFuncional(String ldapAtributoCodFuncional) {
		this.ldapAtributoCodFuncional = ldapAtributoCodFuncional;
	}

	/**
	 * Atribui o the ldap server.
	 *
	 * @param ldapServer
	 *            o novo the ldap server
	 */
	public void setLdapServer(String ldapServer) {
		this.ldapServer = ldapServer;
	}

	/**
	 * Atribui o the ldap user senha.
	 *
	 * @param ldapUserSenha
	 *            o novo the ldap user senha
	 */
	public void setLdapUserSenha(String ldapUserSenha) {
		this.ldapUserSenha = ldapUserSenha;
	}

	/**
	 * Atribui o the ldap base DN.
	 *
	 * @param ldapBaseDN
	 *            o novo the ldap base DN
	 */
	public void setLdapBaseDN(String ldapBaseDN) {
		this.ldapBaseDN = ldapBaseDN;
	}

	/**
	 * Atribui o the ldap user DN.
	 *
	 * @param ldapUserDN
	 *            o novo the ldap user DN
	 */
	public void setLdapUserDN(String ldapUserDN) {
		this.ldapUserDN = ldapUserDN;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LdapConfig [ldapSPorta=" + getLdapSPorta() + ", ldapSProtocolo=" + getLdapSProtocolo() + ", ldapPorta="
				+ getLdapPorta() + ", ldapTipoConexao=" + getLdapTipoConexao() + ", ldapFilter=" + getLdapFilter()
				+ ", ldapDnInativos=" + getLdapDnInativos() + ", ldapAtributos=" + getLdapAtributos()
				+ ", ldapAtributoCodFuncional=" + getLdapAtributoCodFuncional() + ", ldapServer=" + getLdapServer()
				+ ", ldapUserSenha=" + getLdapUserSenha() + ", ldapBaseDN=" + getLdapBaseDN() + ", ldapUserDN=" + getLdapUserDN()
				+ "]";
	}

	
}
