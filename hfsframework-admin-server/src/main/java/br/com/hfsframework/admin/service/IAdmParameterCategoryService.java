package br.com.hfsframework.admin.service;

import java.util.Optional;

import br.com.hfsframework.admin.client.domain.AdmParameterCategoryDTO;
import br.com.hfsframework.admin.domain.AdmParameterCategory;
import br.com.hfsframework.admin.repository.IAdmParameterCategoryRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmParameterCategoryService extends
		IBaseBusinessService<AdmParameterCategoryDTO, AdmParameterCategory, Long, IAdmParameterCategoryRepository> {

	Optional<AdmParameterCategoryDTO> findByDescription(String description);
}
