package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.client.domain.AdmMenuDTO;
import br.com.hfsframework.admin.domain.AdmMenu;
import br.com.hfsframework.admin.repository.IAdmMenuRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmMenuService
		extends BaseBusinessService<AdmMenuDTO, AdmMenu, Long, IAdmMenuRepository>
		implements IAdmMenuService {

	private static final long serialVersionUID = 1L;

}
