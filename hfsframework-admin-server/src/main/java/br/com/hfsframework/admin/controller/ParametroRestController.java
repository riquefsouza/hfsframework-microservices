package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.Parametro;
import br.com.hfsframework.admin.service.ParametroService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/parametro")
public class ParametroRestController extends BaseRestController<Parametro, Long, ParametroService> {

}
