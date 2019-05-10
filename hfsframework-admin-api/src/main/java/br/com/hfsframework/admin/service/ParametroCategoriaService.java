package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.ParametroCategoria;
import br.com.hfsframework.admin.repository.IParametroCategoriaRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class ParametroCategoriaService
		extends BaseBusinessService<ParametroCategoria, Long, IParametroCategoriaRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
