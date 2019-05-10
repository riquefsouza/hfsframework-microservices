package br.com.hfsframework.admin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="ADM_PARAMETRO")
@NamedQueries({
	@NamedQuery(name = "Parametro.getDescricaoById", query = "SELECT c.descricao FROM Parametro c WHERE c.id = ?1"),
	@NamedQuery(name = "Parametro.countNovo", query = "SELECT COUNT(c) FROM Parametro c WHERE LOWER(c.descricao) = ?1"),
	@NamedQuery(name = "Parametro.countAntigo", query = "SELECT COUNT(c) FROM Parametro c WHERE LOWER(c.descricao) <> ?1 AND LOWER(c.descricao) = ?2"),
	@NamedQuery(name = "Parametro.getValorByCodigo", query = "SELECT c.valor FROM Parametro c WHERE c.codigo= ?1")
})
public class Parametro implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name="ADM_PARAMETRO_ID_GENERATOR", sequenceName="ADM_PARAMETRO_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_PARAMETRO_ID_GENERATOR")
	@Column(name="PAR_SEQ")
	private Long id;

	/** The codigo. */
	@Column(name="PAR_CODIGO")
	private String codigo;

	/** The descricao. */
	@NotNull
	@NotBlank
	@NotEmpty	
	@Column(name="PAR_DESCRICAO", unique = true)
	private String descricao;

	/** The valor. */
	@Column(name="PAR_VALOR")
	private String valor;
	
	/** The id adm parametro categoria. */
	@Column(name = "PAR_PMC_SEQ", nullable=false)
	private Long idParametroCategoria;

	/** The adm parametro categoria. */
	//bi-directional many-to-one association to ParametroCategoria
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name="PAR_PMC_SEQ", nullable=false, insertable = false, updatable = false)
	private ParametroCategoria parametroCategoria;

	/**
	 * Instantiates a new adm parametro.
	 */
	public Parametro() {
		limpar();
	}
	
	/**
	 * Instantiates a new adm parametro.
	 *
	 * @param id the id
	 * @param valor the valor
	 * @param descricao the descricao
	 * @param codigo the codigo
	 * @param idParametroCategoria the id parametro categoria
	 */
	public Parametro(Long id, String valor, String descricao, String codigo, Long idParametroCategoria) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.idParametroCategoria = idParametroCategoria;
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.codigo = null;
		this.descricao = null;
		this.valor = null;
		this.parametroCategoria = new ParametroCategoria();
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
	 * Pega o the codigo.
	 *
	 * @return o the codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Atribui o the codigo.
	 *
	 * @param codigo
	 *            o novo the codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * Pega o the valor.
	 *
	 * @return o the valor
	 */
	public String getValor() {
		return this.valor;
	}

	/**
	 * Atribui o the valor.
	 *
	 * @param valor
	 *            o novo the valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Pega o the parametro categoria.
	 *
	 * @return o the parametro categoria
	 */
	public ParametroCategoria getParametroCategoria() {
		return this.parametroCategoria;
	}

	/**
	 * Atribui o the parametro categoria.
	 *
	 * @param parametroCategoria
	 *            o novo the parametro categoria
	 */
	public void setParametroCategoria(ParametroCategoria parametroCategoria) {
		this.parametroCategoria = parametroCategoria;
	}

	/**
	 * Pega o the id parametro categoria.
	 *
	 * @return o the id parametro categoria
	 */
	public Long getIdParametroCategoria() {
		return idParametroCategoria;
	}

	/**
	 * Atribui o the id parametro categoria.
	 *
	 * @param idParametroCategoria
	 *            o novo the id parametro categoria
	 */
	public void setIdParametroCategoria(Long idParametroCategoria) {
		this.idParametroCategoria = idParametroCategoria;
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
		Parametro other = (Parametro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}