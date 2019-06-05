package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.AdmUser;

public interface IAdmUserRepository extends JpaRepository<AdmUser, Long> {

}
