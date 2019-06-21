package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.client.domain.AdmProfileDTO;
import br.com.hfsframework.admin.domain.AdmProfile;
import br.com.hfsframework.admin.repository.IAdmProfileRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmProfileService 
		extends BaseBusinessService<AdmProfileDTO, AdmProfile, Long, IAdmProfileRepository>
		implements IAdmProfileService {

	private static final long serialVersionUID = 1L;

}
