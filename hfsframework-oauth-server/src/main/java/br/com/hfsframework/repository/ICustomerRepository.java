package br.com.hfsframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hfsframework.domain.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
