package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.AdmUserIp;
import br.com.hfsframework.admin.domain.AdmUserIpPK;

public interface IAdmUserIpRepository extends JpaRepository<AdmUserIp, AdmUserIpPK> {

}
