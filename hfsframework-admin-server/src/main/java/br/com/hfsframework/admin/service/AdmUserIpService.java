package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.AdmUserIp;
import br.com.hfsframework.admin.domain.AdmUserIpPK;
import br.com.hfsframework.admin.repository.IAdmUserIpRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmUserIpService
		extends BaseBusinessService<AdmUserIp, AdmUserIpPK, IAdmUserIpRepository>
		implements IAdmUserIpService {

	private static final long serialVersionUID = 1L;

}
