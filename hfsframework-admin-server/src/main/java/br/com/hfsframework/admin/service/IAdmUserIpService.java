package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.domain.AdmUserIp;
import br.com.hfsframework.admin.domain.AdmUserIpPK;
import br.com.hfsframework.admin.repository.IAdmUserIpRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmUserIpService
		extends IBaseBusinessService<AdmUserIp, AdmUserIpPK, IAdmUserIpRepository> {
}
