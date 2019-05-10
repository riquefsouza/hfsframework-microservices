/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.hfsframework.util.exceptions.TransacaoException;

/**
 * The Interface IBaseCrud.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 */
public interface IBaseCrud<T, I extends Serializable> extends Serializable {

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

	/**
	 * Get all.
	 *
	 * @return the list
	 */
	Iterable<T> getAll();
	
	/**
	 * Get all.
	 *
	 * @param p the p
	 * @return the page
	 */
	Page<T> getAll(Pageable p);

	/**
	 * Add.
	 *
	 * @param bean
	 *            the bean
	 * @return the t
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	Optional<T> add(T bean) throws TransacaoException;

	/**
	 * Update.
	 *
	 * @param bean
	 *            the bean
	 * @return the t
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	Optional<T> update(T bean) throws TransacaoException;

	/**
	 * Delete.
	 *
	 * @param bean
	 *            the bean
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	void delete(T bean) throws TransacaoException;
	
}
