package br.com.hfsframework.admin.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.client.domain.AdmParameterCategoryDTO;
import br.com.hfsframework.admin.domain.AdmParameterCategory;
import br.com.hfsframework.admin.repository.IAdmParameterCategoryRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmParameterCategoryService
		extends BaseBusinessService<AdmParameterCategoryDTO, AdmParameterCategory, Long, IAdmParameterCategoryRepository>
		implements IAdmParameterCategoryService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Override
	public Optional<AdmParameterCategoryDTO> findByDescription(String description) {
		Optional<AdmParameterCategory> obj = getRepositorio().findByDescription(description);
		return Optional.of(obj.get().toDTO());
	}

}
