package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.AdmParameterCategory;
import br.com.hfsframework.admin.repository.IAdmParameterCategoryRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmParameterCategoryService
		extends BaseBusinessService<AdmParameterCategory, Long, IAdmParameterCategoryRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

}
