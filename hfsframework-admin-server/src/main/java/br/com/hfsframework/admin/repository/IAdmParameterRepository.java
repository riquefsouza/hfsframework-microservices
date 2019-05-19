package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.AdmParameter;

public interface IAdmParameterRepository extends JpaRepository<AdmParameter, Long> {

}
