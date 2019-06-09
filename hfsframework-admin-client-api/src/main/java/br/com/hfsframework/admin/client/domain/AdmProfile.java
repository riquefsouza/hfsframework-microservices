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

public class AdmProfile implements BaseEntityRestClient<AdmProfile, Long> {

	private String jsonText;
	
	private Long id;

	private Boolean administrator;
	
	@NotBlank
	@Size(min=4, max=255)
	private String description;
	
	private Boolean geral;
	
	private Set<AdmPage> admPages;
	
	private Set<AdmUser> admUsers;
	
	public AdmProfile() {
		super();
		this.admPages = new HashSet<AdmPage>();
		this.admUsers = new HashSet<AdmUser>();		
		this.clear();
	}

	public AdmProfile(Long id) {
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

	public Boolean getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getGeral() {
		return geral;
	}

	public void setGeral(Boolean geral) {
		this.geral = geral;
	}

	public Set<AdmPage> getAdmPages() {
		return admPages;
	}

	public void setAdmPages(Set<AdmPage> admPages) {
		this.admPages = admPages;
	}

	public Set<AdmUser> getAdmUsers() {
		return admUsers;
	}

	public void setAdmUsers(Set<AdmUser> admUsers) {
		this.admUsers = admUsers;
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
		AdmProfile other = (AdmProfile) obj;
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
		this.administrator = false;
		this.description = "";
		this.geral = false;
		this.admPages.clear();
		this.admUsers.clear();		
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmProfile> conv = new JSONConverter<AdmProfile>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmProfile> fromJSON(String jsonText) {
		JSONConverter<AdmProfile> conv = new JSONConverter<AdmProfile>();
		TypeReference<AdmProfile> mapType = new TypeReference<AdmProfile>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmProfile> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmProfile> list) {
		JSONListConverter<AdmProfile> conv = new JSONListConverter<AdmProfile>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmProfile> jsonToLista(String jsonText) {
		JSONListConverter<AdmProfile> conv = new JSONListConverter<AdmProfile>();
		TypeReference<List<AdmProfile>> mapType = new TypeReference<List<AdmProfile>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}