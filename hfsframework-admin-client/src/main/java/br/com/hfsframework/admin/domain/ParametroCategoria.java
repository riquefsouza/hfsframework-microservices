package br.com.hfsframework.admin.domain;

import java.util.ArrayList;
import java.util.List;

public class ParametroCategoria {

	private Long id;

	private String descricao;

	private Long ordem;

	private List<Parametro> parametros;

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

	public void limpar() {
		this.id = null;
		this.descricao = null;
		this.ordem = null;
		this.parametros.clear();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	public List<Parametro> getparametros() {
		return this.parametros;
	}

	public void setparametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getparametros().add(parametro);
		parametro.setParametroCategoria(this);

		return parametro;
	}

	public Parametro removeAdmParametro(Parametro parametro) {
		getparametros().remove(parametro);
		parametro.setParametroCategoria(null);

		return parametro;
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
		ParametroCategoria other = (ParametroCategoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParametroCategoria [id=" + id + ", descricao=" + descricao + ", ordem=" + ordem + ", parametros="
				+ parametros + "]";
	}

}