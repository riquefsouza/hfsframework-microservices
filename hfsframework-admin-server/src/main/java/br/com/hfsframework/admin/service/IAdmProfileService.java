package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.client.domain.AdmProfileDTO;
import br.com.hfsframework.admin.domain.AdmProfile;
import br.com.hfsframework.admin.repository.IAdmProfileRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmProfileService 
	extends IBaseBusinessService<AdmProfileDTO, AdmProfile, Long, IAdmProfileRepository>{

}
