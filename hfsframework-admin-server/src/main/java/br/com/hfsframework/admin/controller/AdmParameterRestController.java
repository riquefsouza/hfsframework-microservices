package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.AdmParameter;
import br.com.hfsframework.admin.service.AdmParameterService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/parametro")
public class AdmParameterRestController extends BaseRestController<AdmParameter, Long, AdmParameterService> {

}
