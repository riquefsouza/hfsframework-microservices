package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.client.domain.AdmUserDTO;
import br.com.hfsframework.admin.domain.AdmUser;
import br.com.hfsframework.admin.repository.IAdmUserRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmUserService
		extends BaseBusinessService<AdmUserDTO, AdmUser, Long, IAdmUserRepository>
		implements IAdmUserService {

	private static final long serialVersionUID = 1L;

}
