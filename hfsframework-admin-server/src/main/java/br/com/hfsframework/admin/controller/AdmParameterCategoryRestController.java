package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.AdmParameterCategory;
import br.com.hfsframework.admin.service.AdmParameterCategoryService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/parametroCategoria")
public class AdmParameterCategoryRestController extends BaseRestController<AdmParameterCategory, Long, AdmParameterCategoryService> {
	
}
