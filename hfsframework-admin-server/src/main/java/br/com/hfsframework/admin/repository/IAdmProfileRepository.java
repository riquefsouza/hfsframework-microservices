package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.AdmProfile;

public interface IAdmProfileRepository extends JpaRepository<AdmProfile, Long> {

}
