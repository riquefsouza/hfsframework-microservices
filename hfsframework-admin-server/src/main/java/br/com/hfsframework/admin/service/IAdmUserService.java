package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.domain.AdmUser;
import br.com.hfsframework.admin.repository.IAdmUserRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmUserService
		extends IBaseBusinessService<AdmUser, Long, IAdmUserRepository> {
}
