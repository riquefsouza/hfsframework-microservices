package br.com.hfsframework.admin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hfsframework.admin.serializer.AdmProfileListSerializer;
import br.com.hfsframework.security.model.PageVO;

@Entity
@Table(name="ADM_PAGE")
@NamedQueries({
	@NamedQuery(name = "AdmPage.getDescriptionById", query = "SELECT c.url FROM AdmPage c WHERE c.id = ?1"),
	@NamedQuery(name = "AdmPage.countNovo", query = "SELECT COUNT(c) FROM AdmPage c WHERE LOWER(c.url) = ?1"),
	@NamedQuery(name = "AdmPage.countAntigo", query = "SELECT COUNT(c) FROM AdmPage c WHERE LOWER(c.url) <> ?1 AND LOWER(c.url) = ?2"),	
	@NamedQuery(name = "AdmPage.findPerfisPorPage", query="SELECT distinct p FROM AdmPage pag inner join pag.admProfiles p where pag = ?1")
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AdmPage implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name="ADM_PAGE_ID_GENERATOR", sequenceName="ADM_PAGE_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_PAGE_ID_GENERATOR")
	@Column(name="PAG_SEQ")
	private Long id;

	/** The description. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name="PAG_DESCRIPTION", unique = true)
	private String description;

	/** The url. */
	@NotNull
	@NotBlank
	@NotEmpty	
	@Column(name="PAG_URL", unique = true)
	private String url;

	/** The adm perfils. */ 
	//bi-directional many-to-many association to AdmProfile
	//@ManyToMany(mappedBy="admPages", fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	//@JsonIgnore
	@JsonSerialize(using = AdmProfileListSerializer.class)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ADM_PAGE_PROFILE", joinColumns = { 
			@JoinColumn(name = "PGL_PAG_SEQ") }, inverseJoinColumns = {@JoinColumn(name = "PGL_PRF_SEQ") })
	private List<AdmProfile> admProfiles;
	
	/** The adm menus. */
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "admPage", fetch = FetchType.EAGER)	
	private List<AdmMenu> admMenus;	
	
	/**
	 * Instantiates a new adm pagina.
	 */
	public AdmPage() {
		super();
		this.admProfiles = new ArrayList<AdmProfile>();
		this.admMenus = new ArrayList<AdmMenu>();
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
	 * Pega o the adm perfils.
	 *
	 * @return o the adm perfils
	 */
	public List<AdmProfile> getAdmProfiles() {
		return this.admProfiles;
	}

	/**
	 * Atribui o the adm perfils.
	 *
	 * @param admProfiles
	 *            o novo the adm perfils
	 */
	public void setAdmProfiles(List<AdmProfile> admProfiles) {
		this.admProfiles = admProfiles;
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
}