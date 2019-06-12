package br.com.hfsframework.admin.service;

import br.com.hfsframework.admin.client.domain.AdmUserProfileDTO;
import br.com.hfsframework.admin.domain.AdmUserProfile;
import br.com.hfsframework.admin.domain.AdmUserProfilePK;
import br.com.hfsframework.admin.repository.IAdmUserProfileRepository;
import br.com.hfsframework.base.IBaseBusinessService;

public interface IAdmUserProfileService
		extends IBaseBusinessService<AdmUserProfileDTO, AdmUserProfile, AdmUserProfilePK, IAdmUserProfileRepository> {
}
