package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.VwAdmLog;

public interface IVwAdmLogRepository extends JpaRepository<VwAdmLog, Long> {

}
