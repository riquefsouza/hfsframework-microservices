/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseResourceRestController.
 *
 * @param <T> the generic type
 * @param <I> the generic type
 * @param <S> the generic type
 * @param <R> the generic type
 */
@Api
public abstract class BaseResourceRestController<D, T extends IBaseToDTO<D>,
	I extends Serializable, 
	S extends BaseBusinessService<D, T, I, ? extends JpaRepository<T, I>>,
	R extends ResourceSupport> {

	/** The log. */
	protected Logger log = LoggerFactory.getLogger(BaseResourceRestController.class);
	
	/** The servico. */
	@Autowired
	protected S servico;

	/**
	 * Get.
	 *
	 * @param id the id
	 * @return the r
	 */
	@ApiOperation("Get with Resource by id")
	@GetMapping(value = "/{id}", produces = "application/json")
	public abstract R get(@PathVariable(value="id") I id);

	/**
	 * Get All.
	 *
	 * @return the resources
	 */
	@ApiOperation("Get all with Resource")
	@GetMapping(produces = "application/json")
	public abstract Resources<R> getAll();

	/**
	 * Pages.
	 *
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
	 * @param id the id
	 * @param bean the bean
	 * @return the response entity
	 */
	@ApiOperation("Update bean")
	@PutMapping("/{id}")
	public ResponseEntity<T> update(@PathVariable I id, @Valid @RequestBody T bean) {
		Optional<T> obj = servico.get(id);

		if (!obj.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		//BeanUtils.copyProperties(bean, obj, "id");

		obj = servico.update(bean);

		return ResponseEntity.ok(obj.get());
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@ApiOperation("Delete bean")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable I id) {
		Optional<T> obj = servico.get(id);

		if (!obj.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		servico.delete(obj.get());

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
