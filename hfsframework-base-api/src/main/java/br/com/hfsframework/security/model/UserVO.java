/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.hfsframework.util.CPFCNPJUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioVO.
 */
@Component
public class UserVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The matricula. */
	private Long id;

	/** The ip. */
	private String ip;
	
	/** The cpf. */
	private BigDecimal cpf;

	/** The email. */
	private String email;

	/** The ldap DN. */
	private String ldapDN;

	/** The login. */
	private String login;

	/** The name. */
	private String name;

	/** The password. */
	private String password;

	/**
	 * Instantiates a new adm usuario.
	 */
	public UserVO() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = 0L;
		this.ip = "";
		this.cpf = BigDecimal.ZERO;
		this.email = "";
		this.ldapDN = "";
		this.login = "";
		this.name = "";
		this.password = "";
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public Long getId() {
		return id;
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
	 * Pega o the ip.
	 *
	 * @return o the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Atribui o the ip.
	 *
	 * @param ip
	 *            o novo the ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * Pega o the cpf.
	 *
	 * @return o the cpf
	 */
	public BigDecimal getCpf() {
		return this.cpf;
	}

	/**
	 * Atribui o the cpf.
	 *
	 * @param cpf
	 *            o novo the cpf
	 */
	public void setCpf(BigDecimal cpf) {
		this.cpf = cpf;
	}

	/**
	 * Pega o the email.
	 *
	 * @return o the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Atribui o the email.
	 *
	 * @param email
	 *            o novo the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Pega o the ldap DN.
	 *
	 * @return o the ldap DN
	 */
	public String getLdapDN() {
		return this.ldapDN;
	}

	/**
	 * Atribui o the ldap DN.
	 *
	 * @param ldapDN
	 *            o novo the ldap DN
	 */
	public void setLdapDN(String ldapDN) {
		this.ldapDN = ldapDN;
	}

	/**
	 * Pega o the login.
	 *
	 * @return o the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Atribui o the login.
	 *
	 * @param login
	 *            o novo the login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Pega o the name.
	 *
	 * @return o the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Atribui o the name.
	 *
	 * @param name
	 *            o novo the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the cpf formatado.
	 *
	 * @return the cpf formatado
	 */
	public String getCpfFormatado() {
		try {
			return CPFCNPJUtil.formatCPForCPNJ(cpf.longValue(), false);
		} catch (Exception e) {
			return this.cpf.toString();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
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
		UserVO other = (UserVO) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
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
		return name;
	}

}
