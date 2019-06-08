package br.com.hfsframework.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.admin.domain.AdmParameterCategory;

public interface IAdmParameterCategoryRepository extends JpaRepository<AdmParameterCategory, Long> {

	Optional<AdmParameterCategory> findByDescription(String description);
}
