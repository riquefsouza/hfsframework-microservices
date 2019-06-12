package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hfsframework.base.report.BaseReportImpl;
import br.com.hfsframework.base.report.IBaseReport;
import br.com.hfsframework.base.report.ReportTypeEnum;
import br.com.hfsframework.base.view.report.BaseViewReportController;
import br.com.hfsframework.base.view.report.ReportParamsDTO;
import br.com.hfsframework.util.AuthenticationUtil;
import br.com.hfsframework.util.exceptions.TransactionException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
public abstract class BaseRestController<D, T extends IBaseToDTO<D>, I extends Serializable, 
	S extends IBaseBusinessService<D, T, I, ? extends JpaRepository<T, I>>> 
	extends BaseViewReportController {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	protected Logger log = LoggerFactory.getLogger(BaseRestController.class);

	@Autowired
	protected S service;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;
	
	@Value("${oauth2.hfsframework.client-id}")
	private String authClientId;
	
	private boolean validateAuthorizationToken;

	public BaseRestController(boolean validateAuthorizationToken) {
		super();
		this.validateAuthorizationToken = validateAuthorizationToken;
		//this.authClientId = authClientId;
	}
	
	private boolean validateAuthorization() {
		if (validateAuthorizationToken) {
			if (AuthenticationUtil.isTokenExpiredOrUnauthorized(request, response, authClientId)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get.
	 *
	 * @param principal the principal
	 * @param id the id
	 * @return the response entity
	 */
	@ApiOperation("Get by id")
	@GetMapping("/{id}")
	public ResponseEntity<D> get(@PathVariable I id) {
		if (validateAuthorization()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Optional<T> obj = service.get(id);

		if (!obj.isPresent()) {
			log.info("GET NOT FOUND: " + id);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj.get().toDTO());
	}

	@ApiOperation("Get all")
	@GetMapping
	public ResponseEntity<List<D>> getAll() {
		if (validateAuthorization()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(service.getAllDTO());
	}

	/**
	 * Pages.
	 *
	 * @param principal the principal
	 * @param p the p
	 * @return the page
	 */
	@ApiOperation("Pages")
	@GetMapping("/pages")
	@ResponseBody
	public ResponseEntity<Page<D>> pages(Pageable p) {
		if (validateAuthorization()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return ResponseEntity.ok(service.getAllDTO(p));
	}

	/**
	 * Insert.
	 *
	 * @param principal the principal
	 * @param bean the bean
	 * @return the optional
	 */
	@ApiOperation("Insert bean")
	@PostMapping
	public ResponseEntity<D> insert(@Valid @RequestBody T bean) {
		if (validateAuthorization()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Optional<T> obj = service.add(bean);

		if (obj.isPresent()) {
			return new ResponseEntity<>(obj.get().toDTO(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(obj.get().toDTO(), HttpStatus.NOT_MODIFIED);
		}
	}

	/**
	 * Update.
	 *
	 * @param principal the principal
	 * @param id the id
	 * @param bean the bean
	 * @return the response entity
	 */
	@ApiOperation("Update bean")
	@PutMapping("/{id}")
	public ResponseEntity<D> update(@PathVariable I id, @Valid @RequestBody T bean) {
		if (validateAuthorization()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Optional<T> obj = service.get(id);

		if (!obj.isPresent()) {
			log.info("UPDATE NOT FOUND: " + bean.toString());
			return ResponseEntity.notFound().build();
		}

		//BeanUtils.copyProperties(bean, obj, "id");

		try {
			obj = service.update(bean);
		} catch (TransactionException e) {
			return new ResponseEntity<>(obj.get().toDTO(), HttpStatus.NOT_MODIFIED);
		}

		return ResponseEntity.ok(obj.get().toDTO());
	}

	/**
	 * Delete.
	 *
	 * @param principal the principal
	 * @param id the id
	 * @return the response entity
	 */
	@ApiOperation("Delete bean")
	@DeleteMapping("/{id}")
	public ResponseEntity<D> delete(@PathVariable I id) {
		if (validateAuthorization()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Optional<T> obj = service.get(id);

		if (!obj.isPresent()) {
			log.info("DELETE NOT FOUND: " + id);
			return ResponseEntity.notFound().build();
		}

		service.delete(obj.get());

		return ResponseEntity.ok(obj.get().toDTO());
		//return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation("Export Report")
	@PostMapping(value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ByteArrayResource> report(HttpServletRequest request, @RequestBody ReportParamsDTO reportParamsDTO) {
		if (validateAuthorization()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Map<String, Object> params = this.getParametros();
		params.put("PARAMETER1", "");

		IBaseReport report = new BaseReportImpl(reportParamsDTO.getReportName());
		this.setReportType(reportParamsDTO.getReportType());
		
		ReportTypeEnum reportType = ReportTypeEnum.valueOf(reportParamsDTO.getReportType());
		//MediaType mediaTypeReport = new MediaType(reportType.getContentType());
		
		//MediaType mediaTypeReport = MediaTypeUtil.getMediaTypeForFileName(
			//	request.getServletContext(), reportParamsDTO.getReportName()+"."+reportType.getFileExtension());
		
		byte[] data = this.export(report, service.getAll(), 
				params, Boolean.parseBoolean(reportParamsDTO.getForceDownload()), false);
		
		ByteArrayResource resource = new ByteArrayResource(data);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + reportParamsDTO.getReportName())
				.contentType(reportType.getMediaType())
				.contentLength(data.length)
				.body(resource);
	}

}
