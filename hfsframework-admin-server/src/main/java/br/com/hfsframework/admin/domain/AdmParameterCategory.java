package br.com.hfsframework.admin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hfsframework.admin.serializer.AdmParameterListSerializer;

@Entity
@Table(name="ADM_PARAMETER_CATEGORY")
@NamedQueries({
	@NamedQuery(name = "AdmParameterCategory.getDescriptionById", query = "SELECT c.description FROM AdmParameterCategory c WHERE c.id = ?1"),
	@NamedQuery(name = "AdmParameterCategory.countNew", query = "SELECT COUNT(c) FROM AdmParameterCategory c WHERE LOWER(c.description) = ?1"),
	@NamedQuery(name = "AdmParameterCategory.countOld", query = "SELECT COUNT(c) FROM AdmParameterCategory c WHERE LOWER(c.description) <> ?1 AND LOWER(c.description) = ?2")
})	
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AdmParameterCategory implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id	
	@GenericGenerator(name = "ADM_PARAMETER_CATEGORY_ID_GENERATOR",
	strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
    	@Parameter(name = "sequence_name", value = "ADM_PARAMETER_CATEGORY_SEQ"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_PARAMETER_CATEGORY_ID_GENERATOR")
	@Column(name="PMC_SEQ")
	private Long id;

	/** The description. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name="PMC_DESCRIPTION", unique = true)
	private String description;

	/** The order. */
	@Column(name="PMC_ORDER")
	private Long order;

	@JsonSerialize(using = AdmParameterListSerializer.class)
	@OneToMany(mappedBy="admParameterCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<AdmParameter> admParameters;

	/**
	 * Instantiates a new parameter category.
	 */
	public AdmParameterCategory() {
		this.admParameters = new ArrayList<AdmParameter>();
		limpar();
	}

	public AdmParameterCategory(Long id) {
		super();
		this.id = id;
	}

	public AdmParameterCategory(Long id, String description, Long order) {
		super();
		this.id = id;
		this.description = description;
		this.order = order;
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.description = null;
		this.order = null;
		this.admParameters.clear();
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
	 * Pega o the order.
	 *
	 * @return o the order
	 */
	public Long getOrder() {
		return this.order;
	}

	/**
	 * Atribui o the order.
	 *
	 * @param order
	 *            o novo the order
	 */
	public void setOrder(Long order) {
		this.order = order;
	}

	/**
	 * Pega o the parameters.
	 *
	 * @return o the parameters
	 */
	public List<AdmParameter> getparameters() {
		return this.admParameters;
	}

	/**
	 * Atribui o the parameters.
	 *
	 * @param parameters
	 *            o novo the parameters
	 */
	public void setparameters(List<AdmParameter> parameters) {
		this.admParameters = parameters;
	}

	/**
	 * Adiciona o parameter.
	 *
	 * @param parameter
	 *            the parameter
	 * @return the parameter
	 */
	public AdmParameter addParameter(AdmParameter parameter) {
		getparameters().add(parameter);
		parameter.setParameterCategory(this);

		return parameter;
	}

	/**
	 * Remove o parameter.
	 *
	 * @param parameter
	 *            the parameter
	 * @return the parameter
	 */
	public AdmParameter removeAdmParameter(AdmParameter parameter) {
		getparameters().remove(parameter);
		parameter.setParameterCategory(null);

		return parameter;
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