package br.com.hfsframework.admin.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hfsframework.admin.serializer.AdmUserIpListSerializer;
import br.com.hfsframework.security.model.UserVO;
import br.com.hfsframework.util.CPFCNPJUtil;

@Entity
@Table(name = "ADM_USER")

@NamedQueries({
	@NamedQuery(name = "AdmUser.findByLogin", query = "SELECT DISTINCT a FROM AdmUser a WHERE a.login=?1"),
	@NamedQuery(name = "AdmUser.login", query = "SELECT a FROM AdmUser a WHERE a.login=?1 AND a.password=?2")
})
@NamedNativeQueries({
	@NamedNativeQuery(name = "AdmUser.findIPByOracle", query = "SELECT SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15) FROM DUAL"),
	@NamedNativeQuery(name = "AdmUser.findIPByPostgreSQL", query = "SELECT substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)"),
	@NamedNativeQuery(name = "AdmUser.setLoginPostgreSQL", query = "select set_config('myvars.usuario_login', ?1, false)" ),
	@NamedNativeQuery(name = "AdmUser.setIPPostgreSQL", query = "select set_config('myvars.usuario_ip', ?1, false)")
})
/*
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "AdmUser.setOracleLoginAndIP", procedureName = "pkg_adm.setar_usuario_ip",
				// resultClasses = { LoginModel.class },
				parameters = { @StoredProcedureParameter(name = "login", type = String.class, mode = ParameterMode.IN),
						@StoredProcedureParameter(name = "password", type = String.class, mode = ParameterMode.IN) })

})
*/

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EntityListeners(AuditingEntityListener.class)
public class AdmUser implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id	
	@GenericGenerator(name = "ADM_USER_ID_GENERATOR",
	strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
    	@Parameter(name = "sequence_name", value = "ADM_USER_SEQ"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
	})		
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_USER_ID_GENERATOR")	
	@Column(name = "USU_SEQ")
	private Long id;

	/** The cpf. */
	@Column(name = "USU_CPF")
	private BigDecimal cpf;

	/** The email. */
	@Column(name = "USU_EMAIL")
	private String email;

	/** The ldap DN. */
	@Column(name = "USU_LDAP_DN")
	private String ldapDN;

	/** The login. */
	@Column(name = "USU_LOGIN")
	private String login;

	/** The name. */
	@Column(name = "USU_NAME")
	private String name;

	/** The password. */
	@JsonIgnore
	@Column(name = "USU_SENHA")	
	private String password;

	/** The adm usuarioIps. */
	//bi-directional many-to-one association to AdmUserIp
	//@JsonIgnore
	@JsonSerialize(using = AdmUserIpListSerializer.class)	
	@OneToMany(mappedBy="admUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<AdmUserIp> admUserIps;
	
	/** The created date. */
	@Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
 
    /** The modified date. */
    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    /** The created by. */
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
 
    /** The modified by. */
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
    
	/** The ip. */
    @JsonIgnore
	@Transient
	private String ip;
	
    @JsonIgnore
    @Transient
	private String passwordAtual;
	
    @JsonIgnore
	@Transient
	private String passwordNova;
	
	@JsonIgnore
	@Transient
	private String confirmaPasswordNova;
    
	/**
	 * Instantiates a new adm usuario.
	 */
	public AdmUser() {
		super();
		this.admUserIps = new ArrayList<AdmUserIp>();
		limpar();
	}

	public AdmUser(Long id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	/**
	 * Instantiates a new adm usuario.
	 *
	 * @param id the id
	 * @param cpf the cpf
	 * @param email the email
	 * @param ldapDN the ldap DN
	 * @param login the login
	 * @param name the name
	 * @param password the password
	 */
	public AdmUser(Long id, String login, String name, BigDecimal cpf, String email, String ldapDN,
			String password) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.email = email;
		this.ldapDN = ldapDN;
		this.login = login;
		this.name = name;
		this.password = password;
	}

	/**
	 * Instantiates a new adm usuario.
	 *
	 * @param u the u
	 */
	public AdmUser(UserVO u) {
		this();
		
		this.id = u.getId();
		this.cpf = u.getCpf();
		this.email = u.getEmail();
		this.ldapDN = u.getLdapDN();
		this.login = u.getLogin();
		this.name = u.getName();
		this.password = u.getPassword();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = 0L;
		this.cpf = BigDecimal.ZERO;
		this.email = "";
		this.ldapDN = "";
		this.login = "";
		this.name = "";
		this.password = "";
		this.admUserIps.clear();
		this.ip = "";		
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public Long getId() {
		return this.id;
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
	 * Pega o the adm usuarioIps.
	 *
	 * @return o the adm usuarioIps
	 */
	public List<AdmUserIp> getAdmUserIps() {
		return this.admUserIps;
	}

	/**
	 * Atribui o the adm usuarioIps.
	 *
	 * @param admUserIps
	 *            o novo the adm usuarioIps
	 */
	public void setAdmUserIps(List<AdmUserIp> admUserIps) {
		this.admUserIps = admUserIps;
	}

	/**
	 * Adiciona o adm usuarioIp.
	 *
	 * @param admUserIp
	 *            the adm usuarioIp
	 * @return the adm usuarioIp
	 */
	public AdmUserIp addAdmUserIp(AdmUserIp admUserIp) {
		getAdmUserIps().add(admUserIp);
		admUserIp.setAdmUser(this);

		return admUserIp;
	}

	/**
	 * Remove o adm usuarioIp.
	 *
	 * @param admUserIp
	 *            the adm usuarioIp
	 * @return the adm usuarioIp
	 */
	public AdmUserIp removeAdmUserIp(AdmUserIp admUserIp) {
		getAdmUserIps().remove(admUserIp);
		admUserIp.setAdmUser(null);

		return admUserIp;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
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
		AdmUser other = (AdmUser) obj;
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

	/**
	 * To usuario VO.
	 *
	 * @return the usuario VO
	 */
	public UserVO toUserVO(){
		UserVO u = new UserVO();

		u.setId(this.getId());
		u.setIp(ip);
		u.setCpf(cpf);
		u.setEmail(email);
		u.setLdapDN(ldapDN);
		u.setLogin(login);
		u.setName(name);
		u.setPassword(password);
		
		return u;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the new modified date
	 */
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getPasswordAtual() {
		return passwordAtual;
	}


	public void setPasswordAtual(String passwordAtual) {
		this.passwordAtual = passwordAtual;
	}


	public String getPasswordNova() {
		return passwordNova;
	}


	public void setPasswordNova(String passwordNova) {
		this.passwordNova = passwordNova;
	}


	public String getConfirmaPasswordNova() {
		return confirmaPasswordNova;
	}


	public void setConfirmaPasswordNova(String confirmaPasswordNova) {
		this.confirmaPasswordNova = confirmaPasswordNova;
	}
 }