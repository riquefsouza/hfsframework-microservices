package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.client.domain.VwAdmLogValueDTO;
import br.com.hfsframework.admin.domain.VwAdmLogValue;
import br.com.hfsframework.admin.repository.IVwAdmLogValueRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IVwAdmLogValueService
		extends IBaseBusinessService<VwAdmLogValueDTO, VwAdmLogValue, Long, IVwAdmLogValueRepository> {
}
