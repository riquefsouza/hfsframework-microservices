package br.com.hfsframework.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.Parametro;

public interface IParametroRepository extends JpaRepository<Parametro, Long> {

}
