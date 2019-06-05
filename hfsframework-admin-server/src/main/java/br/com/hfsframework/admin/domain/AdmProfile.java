package br.com.hfsframework.admin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hfsframework.admin.serializer.AdmPageListSerializer;
import br.com.hfsframework.admin.serializer.AdmUserListSerializer;
import br.com.hfsframework.converter.BooleanToStringConverter;
import br.com.hfsframework.security.model.PageVO;
import br.com.hfsframework.security.model.ProfileVO;
import br.com.hfsframework.security.model.UserVO;

@Entity
@Table(name = "ADM_PROFILE")
@NamedQueries({
	@NamedQuery(name = "AdmProfile.getDescriptionById", query = "SELECT c.description FROM AdmProfile c WHERE c.id = ?1"),
	@NamedQuery(name = "AdmProfile.countNovo", query = "SELECT COUNT(c) FROM AdmProfile c WHERE LOWER(c.description) = ?1"),
	@NamedQuery(name = "AdmProfile.countAntigo", query = "SELECT COUNT(c) FROM AdmProfile c WHERE LOWER(c.description) <> ?1 AND LOWER(c.description) = ?2"),
	@NamedQuery(name = "AdmProfile.findPagesPorProfile", query = "SELECT distinct pag FROM AdmProfile p inner join p.admPages pag where p = ?1"),
	@NamedQuery(name = "AdmProfile.findUsersPorProfile", query = "SELECT distinct fp.id.usuarioSeq FROM AdmProfile p, AdmUserProfile fp where p.id = fp.id.perfilSeq AND p = ?1"),
	@NamedQuery(name = "AdmProfile.findPerfisPorUser", query = "SELECT distinct p FROM AdmProfile p, AdmUserProfile fp where p.id = fp.id.perfilSeq AND fp.id.usuarioSeq = ?1"),	
	@NamedQuery(name = "AdmProfile.findAdminMenuPaiByProfile", query="SELECT DISTINCT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmProfile p INNER JOIN p.admPages f INNER JOIN f.admMenus m WHERE p = ?1 AND m.id <= 9) ORDER BY t.ordem, t.id"),
	@NamedQuery(name = "AdmProfile.findMenuPaiByProfile", query="SELECT DISTINCT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmProfile p INNER JOIN p.admPages f INNER JOIN f.admMenus m WHERE p = ?1 AND m.id > 9) ORDER BY t.ordem, t.id"),
	@NamedQuery(name = "AdmProfile.findAdminMenuByProfile", query="SELECT DISTINCT m FROM AdmProfile p INNER JOIN p.admPages f INNER JOIN f.admMenus m WHERE p = ?1 AND m.id <= 9 AND m.admMenuPai = ?2 ORDER BY m.ordem, m.id"),
	@NamedQuery(name = "AdmProfile.findMenuByProfile", query="SELECT DISTINCT m FROM AdmProfile p INNER JOIN p.admPages f INNER JOIN f.admMenus m WHERE p = ?1 AND m.id > 9 AND m.admMenuPai = ?2 ORDER BY m.ordem, m.id"),
	@NamedQuery(name = "AdmProfile.findAdminMenuPaiByIdPerfis", query="SELECT DISTINCT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmProfile p INNER JOIN p.admPages f INNER JOIN f.admMenus m WHERE p.id IN ?1 AND m.id <= 9) ORDER BY t.id, t.ordem"),
	@NamedQuery(name = "AdmProfile.findMenuPaiByIdPerfis", query="SELECT DISTINCT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmProfile p INNER JOIN p.admPages f INNER JOIN f.admMenus m WHERE p.id IN ?1 AND m.id > 9) ORDER BY t.ordem, t.id"),
	@NamedQuery(name = "AdmProfile.findAdminMenuByIdPerfis", query="SELECT DISTINCT m FROM AdmProfile p INNER JOIN p.admPages f INNER JOIN f.admMenus m WHERE p.id IN ?1 AND m.id <= 9 AND m.admMenuPai = ?2 ORDER BY m.id, m.ordem"),
	@NamedQuery(name = "AdmProfile.findMenuByIdPerfis", query="SELECT DISTINCT m FROM AdmProfile p INNER JOIN p.admPages f INNER JOIN f.admMenus m WHERE p.id IN ?1 AND m.id > 9 AND m.admMenuPai = ?2 ORDER BY m.id, m.ordem")			
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AdmProfile implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name = "ADM_PROFILE_ID_GENERATOR", sequenceName="ADM_PROFILE_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_PROFILE_ID_GENERATOR")
	@Column(name = "PRF_SEQ")
	private Long id;

	/** The administrator. */
	@Convert(converter = BooleanToStringConverter.class)
	@Column(name = "PRF_ADMINISTRATOR")
	private Boolean administrator;

	/** The description. */
	@NotNull
	@NotBlank
	@NotEmpty	
	@Column(name = "PRF_DESCRIPTION", unique = true)
	private String description;

	/** The geral. */
	@Convert(converter = BooleanToStringConverter.class)
	@Column(name = "PRF_GERAL")
	private Boolean geral;

	/** The adm paginas. */
	//@JsonIgnore
	@JsonSerialize(using = AdmPageListSerializer.class)
	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.EAGER) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "ADM_PAGINA_PERFIL", joinColumns = { 
			@JoinColumn(name = "PGL_PRF_SEQ") }, inverseJoinColumns = {	@JoinColumn(name = "PGL_PAG_SEQ") })
	private List<AdmPage> admPages;
	
	/** The adm funcionarios. */
	//@JsonIgnore
	@JsonSerialize(using = AdmUserListSerializer.class)
	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.EAGER) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "ADM_USUARIO_PERFIL", joinColumns = {
			@JoinColumn(name = "USP_PRF_SEQ") }, inverseJoinColumns = { @JoinColumn(name = "USP_USU_SEQ") })
	private List<AdmUser> admUsers;
		

	/**
	 * Instantiates a new adm perfil.
	 */
	public AdmProfile() {
		super();
		this.admPages = new ArrayList<AdmPage>();
		this.admUsers = new ArrayList<AdmUser>();
		limpar();
	}
		
	public AdmProfile(Long id, String description, Boolean administrator, Boolean geral) {
		super();
		this.id = id;
		this.administrator = administrator;
		this.description = description;
		this.geral = geral;
	}

	/**
	 * Instantiates a new adm perfil.
	 *
	 * @param p
	 *            the p
	 */
	public AdmProfile(ProfileVO p) {
		this();
		
		this.id = p.getId();
		this.administrator = p.getAdministrator();
		this.description = p.getDescription();
		this.geral = p.getGeral();
		for (PageVO page : p.getPages()) {
			this.admPages.add(new AdmPage(page));	
		}
		for (UserVO user : p.getUsers()) {
			this.admUsers.add(new AdmUser(user));	
		}
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.administrator = null;
		this.description = null;
		this.geral = null;
		this.admPages.clear();
		this.admUsers.clear();		
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
	 * Pega o the administrator.
	 *
	 * @return o the administrator
	 */
	public Boolean getAdministrator() {
		return this.administrator;
	}

	/**
	 * Atribui o the administrator.
	 *
	 * @param administrator
	 *            o novo the administrator
	 */
	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	/**
	 * Pega o the description.
	 *
	 * @return o the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Atribui o the description.
	 *
	 * @param description
	 *            o novo the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Pega o the geral.
	 *
	 * @return o the geral
	 */
	public Boolean getGeral() {
		return this.geral;
	}

	/**
	 * Atribui o the geral.
	 *
	 * @param geral
	 *            o novo the geral
	 */
	public void setGeral(Boolean geral) {
		this.geral = geral;
	}

	/**
	 * Pega o the adm paginas.
	 *
	 * @return o the adm paginas
	 */
	public List<AdmPage> getAdmPages() {
		return this.admPages;
	}

	/**
	 * Atribui o the adm paginas.
	 *
	 * @param admPages
	 *            o novo the adm paginas
	 */
	public void setAdmPages(List<AdmPage> admPages) {
		this.admPages = admPages;
	}

	/**
	 * Gets the adm usuarios.
	 *
	 * @return the adm usuarios
	 */
	public List<AdmUser> getAdmUsers() {
		return admUsers;
	}

	/**
	 * Sets the adm usuarios.
	 *
	 * @param admUsers
	 *            the new adm usuarios
	 */
	public void setAdmUsers(List<AdmUser> admUsers) {
		this.admUsers = admUsers;
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
		AdmProfile other = (AdmProfile) obj;
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
		return description;
	}
	
	/**
	 * To perfil VO.
	 *
	 * @return the perfil VO
	 */
	public ProfileVO toProfileVO(){
		ProfileVO p = new ProfileVO();
		p.setId(id);
		p.setAdministrator(administrator);
		p.setDescription(description);
		p.setGeral(geral);
		for (AdmPage admPage : admPages) {
			p.getPages().add(admPage.toPageVO());
		}
		for (AdmUser admUser : admUsers) {
			p.getUsers().add(admUser.toUserVO());
		}
		return p;
	}
}