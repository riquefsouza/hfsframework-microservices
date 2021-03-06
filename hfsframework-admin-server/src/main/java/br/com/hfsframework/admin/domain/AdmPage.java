package br.com.hfsframework.admin.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.hfsframework.admin.client.domain.AdmPageDTO;
import br.com.hfsframework.base.IBaseToDTO;
import br.com.hfsframework.security.model.PageVO;

@Entity
@Table(name="ADM_PAGE")
@NamedQueries({
	@NamedQuery(name = "AdmPage.getDescriptionById", query = "SELECT c.url FROM AdmPage c WHERE c.id = ?1"),
	@NamedQuery(name = "AdmPage.countNovo", query = "SELECT COUNT(c) FROM AdmPage c WHERE LOWER(c.url) = ?1"),
	@NamedQuery(name = "AdmPage.countAntigo", query = "SELECT COUNT(c) FROM AdmPage c WHERE LOWER(c.url) <> ?1 AND LOWER(c.url) = ?2"),	
	@NamedQuery(name = "AdmPage.findPerfisPorPage", query="SELECT distinct p FROM AdmPage pag inner join pag.admProfiles p where pag = ?1")
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.ProperatyGenerator.class, property = "id")
public class AdmPage implements Serializable, IBaseToDTO<AdmPageDTO> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id	
	@GenericGenerator(name = "ADM_PAGE_ID_GENERATOR",
	strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
    	@Parameter(name = "sequence_name", value = "ADM_PAGE_SEQ"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
	})		
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_PAGE_ID_GENERATOR")	
	@Column(name="PAG_SEQ")
	private Long id;

	/** The description. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Size(min=4, max=255)
	@Column(name="PAG_DESCRIPTION", unique = true, nullable = false, length = 255)
	private String description;

	/** The url. */
	@NotNull
	@NotBlank
	@NotEmpty	
	@Size(min=4, max=255)
	@Column(name="PAG_URL", unique = true, nullable = false, length = 255)
	private String url;

	/** The adm profiles. */ 
	//bi-directional many-to-many association to AdmProfile
	//@ManyToMany(mappedBy="admPages", fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	//@JsonIgnore
	//@JsonSerialize(using = AdmProfileSetSerializer.class)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ADM_PAGE_PROFILE", joinColumns = { 
			@JoinColumn(name = "PGL_PAG_SEQ") }, inverseJoinColumns = {@JoinColumn(name = "PGL_PRF_SEQ") })
	private Set<AdmProfile> admProfiles;
	
	/** The adm menus. */
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "admPage", fetch = FetchType.EAGER)	
	private Set<AdmMenu> admMenus;	

	/**
	 * Instantiates a new adm pagina.
	 */
	public AdmPage() {
		super();
		this.admProfiles = new HashSet<AdmProfile>();
		this.admMenus = new HashSet<AdmMenu>();
		limpar();
	}
	
	public AdmPage(Long id, String url, String description) {
		super();
		this.id = id;
		this.description = description;
		this.url = url;
	}

	/**
	 * Instantiates a new adm pagina.
	 *
	 * @param p
	 *            the p
	 */
	public AdmPage(PageVO p) {
		this();
		
		this.id = p.getId();
		this.description = p.getDescription();
		this.url = p.getUrl();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.description = null;
		this.url = null;
		this.admProfiles.clear();
		this.admMenus.clear();
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
	 * Pega o the url.
	 *
	 * @return o the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Atribui o the url.
	 *
	 * @param url
	 *            o novo the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Pega o the adm profiles.
	 *
	 * @return o the adm profiles
	 */
	public Set<AdmProfile> getAdmProfiles() {
		return this.admProfiles;
	}

	/**
	 * Atribui o the adm profiles.
	 *
	 * @param admProfiles
	 *            o novo the adm profiles
	 */
	public void setAdmProfiles(Set<AdmProfile> admProfiles) {
		this.admProfiles = admProfiles;
	}
	
	public Set<AdmMenu> getAdmMenus() {
		return admMenus;
	}

	public void setAdmMenus(Set<AdmMenu> admMenus) {
		this.admMenus = admMenus;
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
		AdmPage other = (AdmPage) obj;
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
		return this.url;
	}

	/**
	 * Gets the perfis pagina.
	 *
	 * @return the perfis pagina
	 */
	public String getPerfisPage() {
		String ret = "";
		for (AdmProfile item : getAdmProfiles()) {
			ret = ret.concat(item.getDescription()).concat(", ");
		}
		if (ret != "") {
			ret = ret.substring(0, ret.length() - 2);
		}
		return ret;
	}
	
	/**
	 * To pagina VO.
	 *
	 * @return the pagina VO
	 */
	public PageVO toPageVO(){
		PageVO p = new PageVO();
		p.setId(id);
		p.setDescription(description);
		p.setUrl(url);
		return p;
	}
	
	public AdmPageDTO toDTO() {
		AdmPageDTO dto = new AdmPageDTO();
		dto.setId(id);
		dto.setDescription(description);
		dto.setUrl(url);
		//this.getAdmProfiles().forEach(item -> dto.getAdmProfiles().add(item.getId()));
		//this.getAdmMenus().forEach(item -> dto.getAdmMenus().add(item.getId()));
		
		return dto;
	}
	
}