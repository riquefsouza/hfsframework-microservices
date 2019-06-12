package br.com.hfsframework.base;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBaseBusinessService<D, T extends IBaseToDTO<D>, 
	I extends Serializable, C extends JpaRepository<T, I>>
		extends IBaseCrud<D, T, I> {

}
