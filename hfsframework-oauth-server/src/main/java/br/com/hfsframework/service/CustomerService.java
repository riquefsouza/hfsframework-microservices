package br.com.hfsframework.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hfsframework.domain.Customer;
import br.com.hfsframework.repository.ICustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	/**
	 * get all customers
	 * @return
	 */
	public Iterable<Customer> getCustomers() {
		
		if (customerRepository.count() == 0) {
		
			Customer customer = new Customer();
			customer.setId(1);
			customer.setName("Google Inc");
			customer.setAddress("11600 Amphitheatre Parkway, Mountain View, CA");
			customer.setPhone("111-222-3333");
			customer.setContact("VP");
			customerRepository.save(customer);
			
			customer = new Customer();
			customer.setId(2);
			customer.setName("Amazon Inc");
			customer.setAddress("410 Terry Ave. North, Seattle, WA");
			customer.setPhone("777-222-3333");
			customer.setContact("Sales Manager");
			customerRepository.save(customer);
		}
		
		return customerRepository.findAll();
	}

	/**
	 * get a customer based on the customerId
	 * @param customerId
	 * @return
	 */
	public Optional<Customer> getCustomer(long customerId) {
		return customerRepository.findById(customerId);
	}
	
	/**
	 * update existing customer
	 * @param customerId
	 * @param customer
	 * @return
	 */
	public Customer updateCustomer(long customerId, Customer customer) {
		return customerRepository.save(customer);
		
	}

	/**
	 * add a customer 
	 * @param customer
	 * @return
	 */
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}	
 
	/**
	 * delete a customer
	 * @param customer
	 */
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}
}
