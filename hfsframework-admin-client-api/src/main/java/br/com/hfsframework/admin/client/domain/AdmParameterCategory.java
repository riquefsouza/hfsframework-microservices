package br.com.hfsframework.admin.client.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.hfsframework.base.test.BaseEntityRestClientTest;

public class AdmParameterCategory implements BaseEntityRestClientTest<Long> {

	private Long id;

	private String description;

	private Long order;

	//@JsonManagedReference("admParameter")
	private List<Long> admParameters;

	public AdmParameterCategory() {
		super();
		this.admParameters = new ArrayList<Long>();
		limpar();
	}

	/*
	@JsonCreator
	public admParameterCategory(@JsonProperty("admParameterCategory") Long id ) {
		super();		
	    this.id = id;
	    this.admParameters = new ArrayList<Long>();
	}
	*/

	public AdmParameterCategory(Long id) {
		super();
		this.id = id;
		this.admParameters = new ArrayList<Long>();
	}

	public AdmParameterCategory(Long id, String description, Long order) {
		super();
		this.id = id;
		this.description = description;
		this.order = order;
		this.admParameters = new ArrayList<Long>();
	}

	public void limpar() {
		this.id = null;
		this.description = null;
		this.order = null;
		this.admParameters.clear();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrder() {
		return this.order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public List<Long> getParameters() {
		return this.admParameters;
	}

	public void setParameters(List<Long> parameters) {
		this.admParameters = parameters;
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
		AdmParameterCategory other = (AdmParameterCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParameterCategory [id=" + id + ", description=" + description + ", order=" + order + ", parameters="
				+ admParameters + "]";
	}

}