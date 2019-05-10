package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.Parametro;
import br.com.hfsframework.admin.repository.IParametroRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class ParametroService extends BaseBusinessService<Parametro, Long, IParametroRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
