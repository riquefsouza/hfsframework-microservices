/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.hfsframework.util.exceptions.TransactionException;

/**
 * The Interface IBaseCrud.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 */
public interface IBaseCrud<D, T extends IBaseToDTO<D>, 
	I extends Serializable> extends Serializable {

	/** The Constant ERRO_INSERT. */
	public static final String ERRO_INSERT = "Erro de Transação ao Incluir: ";
	
	/** The Constant ERRO_UPDATE. */
	public static final String ERRO_UPDATE = "Erro de Transação ao Alterar: ";
	
	/** The Constant ERRO_DELETE. */
	public static final String ERRO_DELETE = "Erro de Transação ao Excluir: ";
	
	/**
	 * Get.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	Optional<T> get(I id);
	
	Optional<D> getDTO(I id);

	/**
	 * Get all.
	 *
	 * @return the list
	 */
	List<T> getAll();
	
	List<D> getAllDTO();
	
	/**
	 * Get all.
	 *
	 * @param p the p
	 * @return the page
	 */
	Page<T> getAll(Pageable p);
	
	Page<D> getAllDTO(Pageable p);

	/**
	 * Add.
	 *
	 * @param bean
	 *            the bean
	 * @return the t
	 * @throws TransactionException
	 *             the transacao exception
	 */
	Optional<T> add(T bean) throws TransactionException;
	
	/**
	 * Update.
	 *
	 * @param bean
	 *            the bean
	 * @return the t
	 * @throws TransactionException
	 *             the transacao exception
	 */
	Optional<T> update(T bean) throws TransactionException;
	
	/**
	 * Delete.
	 *
	 * @param bean
	 *            the bean
	 * @throws TransactionException
	 *             the transacao exception
	 */
	void delete(T bean) throws TransactionException;
	
}
