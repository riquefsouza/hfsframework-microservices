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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hfsframework.admin.serializer.ParametroListSerializer;

@Entity
@Table(name="ADM_PARAMETRO_CATEGORIA")
@NamedQueries({
	@NamedQuery(name = "ParametroCategoria.getDescricaoById", query = "SELECT c.descricao FROM ParametroCategoria c WHERE c.id = ?1"),
	@NamedQuery(name = "ParametroCategoria.countNovo", query = "SELECT COUNT(c) FROM ParametroCategoria c WHERE LOWER(c.descricao) = ?1"),
	@NamedQuery(name = "ParametroCategoria.countAntigo", query = "SELECT COUNT(c) FROM ParametroCategoria c WHERE LOWER(c.descricao) <> ?1 AND LOWER(c.descricao) = ?2")
})	
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ParametroCategoria implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name="ADM_PARAMETRO_CATEGORIA_ID_GENERATOR", sequenceName="ADM_PARAMETRO_CATEGORIA_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_PARAMETRO_CATEGORIA_ID_GENERATOR")
	@Column(name="PMC_SEQ")
	private Long id;

	/** The descricao. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name="PMC_DESCRICAO", unique = true)
	private String descricao;

	/** The ordem. */
	@Column(name="PMC_ORDEM")
	private Long ordem;

	/* The parametros. */
	//bi-directional many-to-one association to Parametro
	@JsonSerialize(using = ParametroListSerializer.class)
	@OneToMany(mappedBy="parametroCategoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Parametro> parametros;

	/**
	 * Instantiates a new parametro categoria.
	 */
	public ParametroCategoria() {
		this.parametros = new ArrayList<Parametro>();
		limpar();
	}
		
	public ParametroCategoria(Long id, String descricao, Long ordem) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ordem = ordem;
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.descricao = null;
		this.ordem = null;
		this.parametros.clear();
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
	 * Pega o the descricao.
	 *
	 * @return o the descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Atribui o the descricao.
	 *
	 * @param descricao
	 *            o novo the descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Pega o the ordem.
	 *
	 * @return o the ordem
	 */
	public Long getOrdem() {
		return this.ordem;
	}

	/**
	 * Atribui o the ordem.
	 *
	 * @param ordem
	 *            o novo the ordem
	 */
	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	/**
	 * Pega o the parametros.
	 *
	 * @return o the parametros
	 */
	public List<Parametro> getparametros() {
		return this.parametros;
	}

	/**
	 * Atribui o the parametros.
	 *
	 * @param parametros
	 *            o novo the parametros
	 */
	public void setparametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	/**
	 * Adiciona o parametro.
	 *
	 * @param parametro
	 *            the parametro
	 * @return the parametro
	 */
	public Parametro addParametro(Parametro parametro) {
		getparametros().add(parametro);
		parametro.setParametroCategoria(this);

		return parametro;
	}

	/**
	 * Remove o parametro.
	 *
	 * @param parametro
	 *            the parametro
	 * @return the parametro
	 */
	public Parametro removeAdmParametro(Parametro parametro) {
		getparametros().remove(parametro);
		parametro.setParametroCategoria(null);

		return parametro;
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
		ParametroCategoria other = (ParametroCategoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}