package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.ParametroCategoria;
import br.com.hfsframework.admin.service.ParametroCategoriaService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/parametroCategoria")
public class ParametroCategoriaRestController extends BaseRestController<ParametroCategoria, Long, ParametroCategoriaService> {
	
}
