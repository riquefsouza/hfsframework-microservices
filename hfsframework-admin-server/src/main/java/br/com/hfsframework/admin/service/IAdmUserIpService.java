package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.client.domain.AdmUserIpDTO;
import br.com.hfsframework.admin.domain.AdmUserIp;
import br.com.hfsframework.admin.domain.AdmUserIpPK;
import br.com.hfsframework.admin.repository.IAdmUserIpRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmUserIpService
		extends IBaseBusinessService<AdmUserIpDTO, AdmUserIp, AdmUserIpPK, IAdmUserIpRepository> {
}
