package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.VwAdmLog;
import br.com.hfsframework.admin.repository.IVwAdmLogRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class VwAdmLogService
		extends BaseBusinessService<VwAdmLog, Long, IVwAdmLogRepository>
		implements IVwAdmLogService {

	private static final long serialVersionUID = 1L;

}
