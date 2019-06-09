package br.com.hfsframework.admin.client.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmPage implements BaseEntityRestClient<AdmPage, Long> {

	private String jsonText;
	
	private Long id;
	
	@NotBlank
	@Size(min=4, max=255)
	private String description;

	@NotBlank
	@Size(min=4, max=255)
	private String url;
	
	private Set<AdmProfile> admProfiles;
	
	private Set<AdmMenu> admMenus;
		
	public AdmPage() {
		super();
		admProfiles = new HashSet<AdmProfile>();
		admMenus = new HashSet<AdmMenu>();
		this.clear();
	}

	public AdmPage(Long id) {
		super();
	    this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getJsonText() {
		return this.jsonText;
	}

	@Override
	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;		
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

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

	@Override
	public void clear() {
		this.jsonText = "";
		this.id = null;
		this.description = "";
		this.url = "";
		admProfiles.clear();
		admMenus.clear();
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmPage> conv = new JSONConverter<AdmPage>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmPage> fromJSON(String jsonText) {
		JSONConverter<AdmPage> conv = new JSONConverter<AdmPage>();
		TypeReference<AdmPage> mapType = new TypeReference<AdmPage>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmPage> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmPage> list) {
		JSONListConverter<AdmPage> conv = new JSONListConverter<AdmPage>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmPage> jsonToLista(String jsonText) {
		JSONListConverter<AdmPage> conv = new JSONListConverter<AdmPage>();
		TypeReference<List<AdmPage>> mapType = new TypeReference<List<AdmPage>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<AdmProfile> getAdmProfiles() {
		return admProfiles;
	}

	public void setAdmProfiles(Set<AdmProfile> admProfiles) {
		this.admProfiles = admProfiles;
	}

	public Set<AdmMenu> getAdmMenus() {
		return admMenus;
	}

	public void setAdmMenus(Set<AdmMenu> admMenus) {
		this.admMenus = admMenus;
	}

}