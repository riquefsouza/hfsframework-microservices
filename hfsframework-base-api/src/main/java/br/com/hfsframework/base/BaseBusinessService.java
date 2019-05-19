/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.ApplicationUtil;
import br.com.hfsframework.util.exceptions.TransactionException;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseBusinessService.
 *
 * @param <T> the generic type
 * @param <I> the generic type
 * @param <C> the generic type
 */
public abstract class BaseBusinessService<T, I extends Serializable, C extends JpaRepository<T, I>>
		implements IBaseBusinessService<T, I, C> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	protected Logger log = LoggerFactory.getLogger(BaseBusinessService.class);

	/** The repositorio. */
	@Autowired
	protected C repositorio;

	/** The aplicacao util. */
	@Autowired
	protected ApplicationUtil aplicacaoUtil;
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#get(java.io.Serializable)
	 */
	@Override
	public Optional<T> get(I id) {
		Optional<T> obj = repositorio.findById(id);
		
		if (obj!=null) {
			log.info("GET: " + obj.toString());			
			return obj;
		} else {
			log.info("GET EMPTY: " + id);			
			return Optional.empty();
		}		
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#getAll()
	 */
	@Override
	public Iterable<T> getAll() {
		return repositorio.findAll();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#getAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<T> getAll(Pageable p) {
		return repositorio.findAll(p);
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#delete(java.lang.Object)
	 */
	@Override
	@Transactional
	public void delete(T bean) throws TransactionException {
		try {
			log.info("DELETE: " + bean.toString());
			
			repositorio.delete(bean);
		} catch (Exception e) {
			throw new TransactionException(log, ERRO_DELETE + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#add(java.lang.Object)
	 */
	@Override
	@Transactional
	public Optional<T> add(T bean) throws TransactionException {
		try {
			T obj = repositorio.save(bean);
			
			if (obj!=null) {
				log.info("INSERT: " + obj.toString());
				
				return Optional.of(obj);
			} else {
				log.info("INSERT EMPTY: " + bean.toString());
				
				return Optional.empty();
			}
		} catch (Exception e) {
			throw new TransactionException(log, ERRO_INSERT + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#update(java.lang.Object)
	 */
	@Override
	@Transactional
	public Optional<T> update(T bean) throws TransactionException {
		try {
			T obj = repositorio.save(bean);
			
			if (obj!=null) {
				log.info("UPDATE: " + obj.toString());
				
				return Optional.of(obj);
			} else {
				log.info("UPDATE EMPTY: " + bean.toString());
				
				return Optional.empty();
			}
		} catch (Exception e) {
			throw new TransactionException(log, ERRO_UPDATE + e.getMessage(), e);
		}
	}

	/**
	 * Pega o the repositorio.
	 *
	 * @return o the repositorio
	 */
	public C getRepositorio() {
		return repositorio;
	}

}
