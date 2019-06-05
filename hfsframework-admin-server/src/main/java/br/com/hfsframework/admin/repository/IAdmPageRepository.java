package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.AdmPage;

public interface IAdmPageRepository extends JpaRepository<AdmPage, Long> {

}
