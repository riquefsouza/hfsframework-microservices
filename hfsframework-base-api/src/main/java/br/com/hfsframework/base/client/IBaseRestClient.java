package br.com.hfsframework.base.client;

import java.util.List;
import java.util.Optional;

import br.com.hfsframework.base.view.report.ReportParamsDTO;

public interface IBaseRestClient<T, I> {

	boolean init(String server, String sAccesToken, Class<T> classEntity);
	
	Optional<T> add(T bean);
	
	List<T> getAll();
	
	Optional<T> getById(I id);
	
	Optional<T> updateById(T bean);
	
	boolean deleteById(I id);
	
	Optional<byte[]> report(ReportParamsDTO reportParamsDTO);
}
