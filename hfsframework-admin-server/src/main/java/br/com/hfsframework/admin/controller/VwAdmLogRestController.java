package br.com.hfsframework.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.admin.domain.VwAdmLog;
import br.com.hfsframework.admin.service.IVwAdmLogService;
import br.com.hfsframework.base.BaseRestController;

@RestController
@RequestMapping("/api/v1/vwAdmLog")
public class VwAdmLogRestController
		extends BaseRestController<VwAdmLog, Long, IVwAdmLogService> {

	private static final long serialVersionUID = 1L;

	public VwAdmLogRestController() {
		super(true);
	}
}
