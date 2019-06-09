package br.com.hfsframework.admin.client.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.hfsframework.base.client.BaseEntityRestClient;
import br.com.hfsframework.util.converter.JSONConverter;
import br.com.hfsframework.util.converter.JSONListConverter;

public class AdmMenu implements BaseEntityRestClient<AdmMenu, Long> {

	private String jsonText;
	
	private Long id;
	
	@NotBlank
	@Size(min=4, max=255)
	private String description;
	
	private Integer order;
	
	private Long idPage;
	
	private AdmPage admPage;
	
	private AdmMenu admMenuParent;
	
	private List<AdmMenu> admSubMenus;

	public AdmMenu() {
		super();
		admSubMenus = new ArrayList<AdmMenu>();
		admPage = new AdmPage();
		this.clear();
	}

	public AdmMenu(Long id) {
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdPage() {
		return idPage;
	}

	public void setIdPage(Long idPage) {
		this.idPage = idPage;
	}

	public AdmPage getAdmPage() {
		return admPage;
	}

	public void setAdmPage(AdmPage admPage) {
		this.admPage = admPage;
	}

	public AdmMenu getAdmMenuParent() {
		return admMenuParent;
	}

	public void setAdmMenuParent(AdmMenu admMenuParent) {
		this.admMenuParent = admMenuParent;
	}

	public List<AdmMenu> getAdmSubMenus() {
		return admSubMenus;
	}

	public void setAdmSubMenus(List<AdmMenu> admSubMenus) {
		this.admSubMenus = admSubMenus;
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
		AdmMenu other = (AdmMenu) obj;
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
		description = "";		
		order = null;
		idPage = null;
		admPage.clear();
		admMenuParent = null;
		admSubMenus.clear();		
	}

	@Override
	public String toJSON() {
		JSONConverter<AdmMenu> conv = new JSONConverter<AdmMenu>();
		return conv.toJSON(this);
	}

	@Override
	public Optional<AdmMenu> fromJSON(String jsonText) {
		JSONConverter<AdmMenu> conv = new JSONConverter<AdmMenu>();
		TypeReference<AdmMenu> mapType = new TypeReference<AdmMenu>() {};		
		return conv.jsonToObject(jsonText, mapType);
	}

	@Override
	public Optional<AdmMenu> fromJSON() {
		if (!this.jsonText.isEmpty()) {
			return this.fromJSON(this.jsonText);
		}
		return Optional.empty();
	}

	@Override
	public String listToJSON(List<AdmMenu> list) {
		JSONListConverter<AdmMenu> conv = new JSONListConverter<AdmMenu>();
		return conv.listToJSON(list);
	}

	@Override
	public List<AdmMenu> jsonToLista(String jsonText) {
		JSONListConverter<AdmMenu> conv = new JSONListConverter<AdmMenu>();
		TypeReference<List<AdmMenu>> mapType = new TypeReference<List<AdmMenu>>() {};
		return conv.jsonToList(jsonText, mapType);
	}

}