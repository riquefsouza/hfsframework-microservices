package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.AdmParameter;
import br.com.hfsframework.admin.repository.IAdmParameterRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmParameterService extends BaseBusinessService<AdmParameter, Long, IAdmParameterRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
