package br.com.hfsframework.admin.client.domain;

import br.com.hfsframework.base.test.BaseEntityRestClientTest;

public class AdmParameter implements BaseEntityRestClientTest<Long> {

	private Long id;

	private String code;

	private String description;

	private String value;

	private Long idAdmParameterCategory;

	//@JsonDeserialize(using = ParameterCategoryDeserializer.class)
	//@JsonBackReference("parameter")
	private AdmParameterCategory admParameterCategory;

	public AdmParameter() {
		super();
		limpar();
	}

	public AdmParameter(Long id) {
		super();
	    this.id = id;
	}
	
	public AdmParameter(Long id, String value, String description, String code, Long idParameterCategory, AdmParameterCategory parameterCategory) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.value = value;
		this.idAdmParameterCategory = idParameterCategory;
		this.admParameterCategory = parameterCategory;
	}

	public void limpar() {
		this.id = null;
		this.code = null;
		this.description = null;
		this.value = null;
		this.admParameterCategory = new AdmParameterCategory();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.code;
	}

	public void setCodigo(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public AdmParameterCategory getParameterCategory() {
		return this.admParameterCategory;
	}

	public void setParameterCategory(AdmParameterCategory parameterCategory) {
		this.admParameterCategory = parameterCategory;
	}

	public Long getIdParameterCategory() {
		return idAdmParameterCategory;
	}

	public void setIdParameterCategory(Long idParameterCategory) {
		this.idAdmParameterCategory = idParameterCategory;
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
		AdmParameter other = (AdmParameter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parameter [id=" + id + ", code=" + code + ", description=" + description + ", value=" + value
				+ ", idParameterCategory=" + idAdmParameterCategory + ", parameterCategory=" + admParameterCategory + "]";
	}

}