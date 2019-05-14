package br.com.hfsframework.admin.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.hfsframework.base.BaseEntityRestClient;

public class ParametroCategoria implements BaseEntityRestClient<Long> {

	private Long id;

	private String descricao;

	private Long ordem;

	//@JsonManagedReference("parametro")
	private List<Long> parametros;

	public ParametroCategoria() {
		super();
		this.parametros = new ArrayList<Long>();
		limpar();
	}

	/*
	@JsonCreator
	public ParametroCategoria(@JsonProperty("parametroCategoria") Long id ) {
		super();		
	    this.id = id;
	    this.parametros = new ArrayList<Long>();
	}
	*/

	public ParametroCategoria(Long id) {
		super();
		this.id = id;
		this.parametros = new ArrayList<Long>();
	}

	public ParametroCategoria(Long id, String descricao, Long ordem) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ordem = ordem;
		this.parametros = new ArrayList<Long>();
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

	public List<Long> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Long> parametros) {
		this.parametros = parametros;
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