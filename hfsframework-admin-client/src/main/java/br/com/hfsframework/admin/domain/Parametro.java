package br.com.hfsframework.admin.domain;

public class Parametro {

	private Long id;

	private String codigo;

	private String descricao;

	private String valor;

	private Long idParametroCategoria;

	private ParametroCategoria parametroCategoria;

	public Parametro() {
		limpar();
	}

	public Parametro(Long id, String valor, String descricao, String codigo, Long idParametroCategoria) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.idParametroCategoria = idParametroCategoria;
	}

	public void limpar() {
		this.id = null;
		this.codigo = null;
		this.descricao = null;
		this.valor = null;
		this.parametroCategoria = new ParametroCategoria();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ParametroCategoria getParametroCategoria() {
		return this.parametroCategoria;
	}

	public void setParametroCategoria(ParametroCategoria parametroCategoria) {
		this.parametroCategoria = parametroCategoria;
	}

	public Long getIdParametroCategoria() {
		return idParametroCategoria;
	}

	public void setIdParametroCategoria(Long idParametroCategoria) {
		this.idParametroCategoria = idParametroCategoria;
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
		Parametro other = (Parametro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parametro [id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", valor=" + valor
				+ ", idParametroCategoria=" + idParametroCategoria + ", parametroCategoria=" + parametroCategoria + "]";
	}

}