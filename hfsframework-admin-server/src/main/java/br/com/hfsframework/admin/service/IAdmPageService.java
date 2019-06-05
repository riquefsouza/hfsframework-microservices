package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.domain.AdmPage;
import br.com.hfsframework.admin.repository.IAdmPageRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmPageService
		extends IBaseBusinessService<AdmPage, Long, IAdmPageRepository> {
}
