package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.client.domain.VwAdmLogDTO;
import br.com.hfsframework.admin.domain.VwAdmLog;
import br.com.hfsframework.admin.repository.IVwAdmLogRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IVwAdmLogService
		extends IBaseBusinessService<VwAdmLogDTO, VwAdmLog, Long, IVwAdmLogRepository> {
}
