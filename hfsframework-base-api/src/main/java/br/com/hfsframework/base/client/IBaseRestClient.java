package br.com.hfsframework.base.client;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestClientException;

import br.com.hfsframework.base.view.report.ReportParamsDTO;

public interface IBaseRestClient<T, I> {

	boolean init(String server, String sAccesToken, Class<T> classEntity) throws RestClientException;
	
	Optional<T> add(T bean) throws RestClientException;
	
	List<T> getAll() throws RestClientException;
	
	Optional<T> getById(I id) throws RestClientException;
	
	Optional<T> updateById(T bean) throws RestClientException;
	
	boolean deleteById(I id) throws RestClientException;
	
	Optional<byte[]> report(ReportParamsDTO reportParamsDTO) throws RestClientException;
}
