package br.com.hfsframework.oauth.client.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.hfsframework.base.client.BaseEntityRestClient;

public class Role implements BaseEntityRestClient<Long> {
	 
	private Long id;

	@NotBlank
	@Size(min=4, max=64)
	private String name;

	public Role() {
		super();
	}
	
	public Role(String name) {
		super();
		this.name = name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
