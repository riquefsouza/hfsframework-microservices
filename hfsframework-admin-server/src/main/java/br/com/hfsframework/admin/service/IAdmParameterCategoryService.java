package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.domain.AdmParameterCategory;
import br.com.hfsframework.admin.repository.IAdmParameterCategoryRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmParameterCategoryService
		extends IBaseBusinessService<AdmParameterCategory, Long, IAdmParameterCategoryRepository> {

}