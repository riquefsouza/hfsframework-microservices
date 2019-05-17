package br.com.hfsframework.oauth.client.domain;

import br.com.hfsframework.base.BaseEntityRestClient;

public class Role implements BaseEntityRestClient<Long> {
	 
	private Long id;

	private String name;

	public Role() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
		
	public Role(String name) {
		this.name = name;
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
