package br.com.hfsframework.base;

public interface BaseEntityRestClient<I> {

	public I getId();
	
	public void setId(I id);
	
	public String getDescricao();

	public void setDescricao(String descricao);

}
