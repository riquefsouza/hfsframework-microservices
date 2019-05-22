package br.com.hfsframework.useful.ldap;

public class LdapAttributes {

	private Long registration;

	private String login;

	private String name;

	private String email;

	private String ldapDN;
	
	public LdapAttributes() {
		super();
		this.registration = 0L;
		this.login = "";
		this.name = "";
		this.email = "";
		this.ldapDN = "";
	}

	public Long getRegistration() {
		return registration;
	}

	public void setRegistration(Long registration) {
		this.registration = registration;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLdapDN() {
		return ldapDN;
	}

	public void setLdapDN(String ldapDN) {
		this.ldapDN = ldapDN;
	}

}
