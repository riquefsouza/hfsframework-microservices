package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.AdmMenu;

public interface IAdmMenuRepository extends JpaRepository<AdmMenu, Long> {

}
