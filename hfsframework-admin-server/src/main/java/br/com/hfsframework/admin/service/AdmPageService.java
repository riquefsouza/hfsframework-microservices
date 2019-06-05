package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.AdmPage;
import br.com.hfsframework.admin.repository.IAdmPageRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmPageService
		extends BaseBusinessService<AdmPage, Long, IAdmPageRepository>
		implements IAdmPageService {

	private static final long serialVersionUID = 1L;

}
