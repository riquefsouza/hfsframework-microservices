package br.com.hfsframework.base.client;

import java.util.List;
import java.util.Optional;

public interface BaseEntityRestClient<T, I> {

	public I getId();
	
	public void setId(I id);

	public String getJsonText();
	
	public void setJsonText(String jsonText);
	
	public void clear();
	
	public String toJSON();
	
	public Optional<T> fromJSON(String jsonText);
	
	public Optional<T> fromJSON();
	
	public String listToJSON(List<T> list);
	
	public List<T> jsonToLista(String jsonText);
}
