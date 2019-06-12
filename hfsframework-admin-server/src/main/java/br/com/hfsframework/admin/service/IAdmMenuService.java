package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.client.domain.AdmMenuDTO;
import br.com.hfsframework.admin.domain.AdmMenu;
import br.com.hfsframework.admin.repository.IAdmMenuRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmMenuService
		extends IBaseBusinessService<AdmMenuDTO, AdmMenu, Long, IAdmMenuRepository> {
}
