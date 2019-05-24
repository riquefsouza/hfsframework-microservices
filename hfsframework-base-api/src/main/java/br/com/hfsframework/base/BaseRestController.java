package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
import br.com.hfsframework.base.view.report.BaseViewReportController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
public abstract class BaseRestController<T, I extends Serializable, 
	S extends IBaseBusinessService<T, I, ? extends JpaRepository<T, I>>> {

	/** The log. */
	protected Logger log = LoggerFactory.getLogger(BaseRestController.class);

	/** The servico. */
	@Autowired
	protected S servico;

	@Autowired
	private BaseViewReportController reportController;

	public S getServico() {
		return servico;
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
	public ResponseEntity<T> get(@PathVariable I id) {
		Optional<T> obj = servico.get(id);

		if (!obj.isPresent()) {
			log.info("GET NOT FOUND: " + id);
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj.get());
	}

	/**
	 * GetAll.
	 *
	 * @param principal the principal
	 * @return the iterable
	 */
	@ApiOperation("Get all")
	@GetMapping
	public ResponseEntity<Iterable<T>> getAll() {
		Iterable<T> iter = servico.getAll();

		return ResponseEntity.ok(iter);
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
	public Page<T> pages(Pageable p) {
		return servico.getAll(p);
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
	public ResponseEntity<T> insert(@Valid @RequestBody T bean) {
		Optional<T> obj = servico.add(bean);

		if (obj.isPresent()) {
			return new ResponseEntity<>(obj.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(obj.get(), HttpStatus.NOT_MODIFIED);
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
	public ResponseEntity<T> update(@PathVariable I id, @Valid @RequestBody T bean) {
		Optional<T> obj = servico.get(id);

		if (!obj.isPresent()) {
			log.info("UPDATE NOT FOUND: " + bean.toString());
			return ResponseEntity.notFound().build();
		}

		//BeanUtils.copyProperties(bean, obj, "id");

		obj = servico.update(bean);

		return ResponseEntity.ok(obj.get());
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
	public ResponseEntity<I> delete(@PathVariable I id) {
		Optional<T> obj = servico.get(id);

		if (!obj.isPresent()) {
			log.info("DELETE NOT FOUND: " + id);
			return ResponseEntity.notFound().build();
		}

		servico.delete(obj.get());

		return new ResponseEntity<>(id, HttpStatus.OK);
		//return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	/*
	@ApiOperation("Export Report")
	@GetMapping(value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void report(@RequestBody ReportParamsDTO reportParamsDTO)  {

		Map<String, Object> params = reportController.getParametros();
		params.put("PARAMETER1", "");

		IBaseReport report = new BaseReportImpl(reportParamsDTO.getReportName());
		
		reportController.export(report, servico.getAll(), 
				params, Boolean.parseBoolean(reportParamsDTO.getForceDownload()), true);		
	}
	*/
	
	@ApiOperation("Export Report")
	//@GetMapping(value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
	@GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] report()  {	

		Map<String, Object> params = reportController.getParametros();
		params.put("PARAMETER1", "");

		//IBaseReport report = new BaseReportImpl(reportParamsDTO.getReportName());
		IBaseReport report = new BaseReportImpl("AutRole");
		
		return reportController.export(report, servico.getAll(), params, true, true);
				//params, Boolean.parseBoolean(reportParamsDTO.getForceDownload()), true);		
	    
	}
	

}
