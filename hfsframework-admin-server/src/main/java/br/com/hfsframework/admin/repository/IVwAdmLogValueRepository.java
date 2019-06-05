package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.VwAdmLogValue;

public interface IVwAdmLogValueRepository extends JpaRepository<VwAdmLogValue, Long> {

}
