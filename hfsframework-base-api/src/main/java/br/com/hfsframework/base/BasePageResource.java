/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.base;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class BasePageResource.
 *
 * @param <T> the generic type
 */
public class BasePageResource<T> extends ResourceSupport implements Page<T> {
	
	/** The page. */
	private final Page<T> page;
	
	/**
	 * Instantiates a new base page resource.
	 *
	 * @param page the page
	 * @param pageParam the page param
	 * @param sizeParam the size param
	 */
	public BasePageResource(Page<T> page, String pageParam,
			String sizeParam) {
		super();
		this.page = page;
		if(page.hasPrevious()) {
			String path = createBuilder()
				.queryParam(pageParam,page.getNumber()-1)
				.queryParam(sizeParam,page.getSize())
				.build()
				.toUriString();
			Link link = new Link(path, Link.REL_PREVIOUS);
			add(link);
		}
		if(page.hasNext()) {
			String path = createBuilder()
				.queryParam(pageParam,page.getNumber()+1)
				.queryParam(sizeParam,page.getSize())
				.build()
				.toUriString();
			Link link = new Link(path, Link.REL_NEXT);
			add(link);
		}
		
		Link link = buildPageLink(pageParam,0,sizeParam,page.getSize(),Link.REL_FIRST);
		add(link);
		
		int indexOfLastPage = page.getTotalPages() - 1;
		link = buildPageLink(pageParam,indexOfLastPage,sizeParam,page.getSize(),Link.REL_LAST);
		add(link);
		
		link = buildPageLink(pageParam,page.getNumber(),sizeParam,page.getSize(),Link.REL_SELF);
		add(link);
	}
	
	/**
	 * Creates the builder.
	 *
	 * @return the servlet uri components builder
	 */
	private ServletUriComponentsBuilder createBuilder() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri();
	}
	
	/**
	 * Builds the page link.
	 *
	 * @param pageParam the page param
	 * @param page the page
	 * @param sizeParam the size param
	 * @param size the size
	 * @param rel the rel
	 * @return the link
	 */
	private Link buildPageLink(String pageParam,int page,String sizeParam,int size,String rel) {
		String path = createBuilder()
				.queryParam(pageParam,page)
				.queryParam(sizeParam,size)
				.build()
				.toUriString();
		Link link = new Link(path,rel);
		return link;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getNumber()
	 */
	@Override
	public int getNumber() {
		return page.getNumber();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getSize()
	 */
	@Override
	public int getSize() {
		return page.getSize();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Page#getTotalPages()
	 */
	@Override
	public int getTotalPages() {
		return page.getTotalPages();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getNumberOfElements()
	 */
	@Override
	public int getNumberOfElements() {
		return page.getNumberOfElements();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Page#getTotalElements()
	 */
	@Override
	public long getTotalElements() {
		return page.getTotalElements();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasPrevious()
	 */
	@Override
	public boolean hasPrevious() {
		return page.hasPrevious();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#isFirst()
	 */
	@Override
	public boolean isFirst() {
		return page.isFirst();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return page.hasNext();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#isLast()
	 */
	@Override
	public boolean isLast() {
		return page.isLast();
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return page.iterator();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getContent()
	 */
	@Override
	public List<T> getContent() {
		return page.getContent();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#hasContent()
	 */
	@Override
	public boolean hasContent() {
		return page.hasContent();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#getSort()
	 */
	@Override
	public Sort getSort() {
		return page.getSort();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#nextPageable()
	 */
	@Override
	public Pageable nextPageable() {
		return page.nextPageable();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Slice#previousPageable()
	 */
	@Override
	public Pageable previousPageable() {
		return page.previousPageable();
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.Page#map(java.util.function.Function)
	 */
	@Override
	public <U> Page<U> map(Function<? super T, ? extends U> converter) {
		return page.map(converter);
	}
	
}
