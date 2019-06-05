package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.VwAdmLogValue;
import br.com.hfsframework.admin.repository.IVwAdmLogValueRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class VwAdmLogValueService
		extends BaseBusinessService<VwAdmLogValue, Long, IVwAdmLogValueRepository>
		implements IVwAdmLogValueService {

	private static final long serialVersionUID = 1L;

}
