/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.base;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.util.ReflectionUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseResourceSupport.
 *
 * @param <T> the generic type
 * @param <I> the generic type
 * @param <C> the generic type
 */
public abstract class BaseResourceSupport<T, I extends Serializable, 
	C extends BaseResourceRestController<T, I, ?, ?>>
		extends ResourceSupport {

	/** The Constant LIST_ALL. */
	public static final String LIST_ALL = "getAll";

	/** The bean. */
	private final T bean;

	/**
	 * Instantiates a new base resource support.
	 *
	 * @param controller the controller
	 * @param bean the bean
	 */
	public BaseResourceSupport(C controller, T bean) {
		this.bean = bean;

		Method metodo = ReflectionUtils.findMethod(bean.getClass(), "getId");
		Long id = (Long) ReflectionUtils.invokeMethod(metodo, bean);
		
		this.add(linkTo(methodOn(controller.getClass()).get(id)).withSelfRel());
		this.add(linkTo(methodOn(controller.getClass()).getAll()).withRel(LIST_ALL));
	}

	/**
	 * Gets the bean.
	 *
	 * @return the bean
	 */
	public T getBean() {
		return bean;
	}
	
	/*
	public Resources<BaseResourceSupport> getLista(Iterable<T> lista) {
		Stream<T> stream = StreamSupport.stream(lista.spliterator(), false);

		List<BaseResourceSupport> listaResource = stream.map(BaseResourceSupport::new)
				.collect(Collectors.toList());

		return new Resources<BaseResourceSupport>(listaResource);
	}
	 */
}
