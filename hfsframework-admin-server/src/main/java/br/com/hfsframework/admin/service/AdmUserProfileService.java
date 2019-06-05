package br.com.hfsframework.admin.service;

import org.springframework.stereotype.Service;

import br.com.hfsframework.admin.domain.AdmUserProfile;
import br.com.hfsframework.admin.domain.AdmUserProfilePK;
import br.com.hfsframework.admin.repository.IAdmUserProfileRepository;
import br.com.hfsframework.base.BaseBusinessService;

@Service
public class AdmUserProfileService
		extends BaseBusinessService<AdmUserProfile, AdmUserProfilePK, IAdmUserProfileRepository>
		implements IAdmUserProfileService {

	private static final long serialVersionUID = 1L;

}
