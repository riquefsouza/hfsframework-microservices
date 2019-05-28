package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.domain.AdmParameter;
import br.com.hfsframework.admin.repository.IAdmParameterRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmParameterService
		extends IBaseBusinessService<AdmParameter, Long, IAdmParameterRepository> {

}
