package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.AdmUserProfile;
import br.com.hfsframework.admin.domain.AdmUserProfilePK;

public interface IAdmUserProfileRepository extends JpaRepository<AdmUserProfile, AdmUserProfilePK> {

}
