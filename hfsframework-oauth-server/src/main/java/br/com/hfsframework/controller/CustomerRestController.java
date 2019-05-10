package br.com.hfsframework.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hfsframework.domain.Customer;
import br.com.hfsframework.service.CustomerService;
 
@RestController
@RequestMapping("/api/v1/")
public class CustomerRestController {
	
	private static final Logger log = LogManager.getLogger(CustomerRestController.class);
	
	@Autowired
	private CustomerService customerService;
 
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-base/customers
     * HTTP method: GET
     * 
     */	
	@RequestMapping(value="/customers", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomers() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//HfsUserDetails principal = (HfsUserDetails) authentication.getPrincipal();
		//log.info("#### logged in user name #### : " + principal.getUsername());
		log.info("#### logged in user name #### : " + authentication.getName());
		
		Iterable<Customer> customerList = customerService.getCustomers();
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}
 
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/customers/{customerId}
     * HTTP method: GET
     * 
     */		
	@RequestMapping(value="/customers/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomer(@PathVariable long customerId) {
		Optional<Customer> customer = customerService.getCustomer(customerId);
		return new ResponseEntity<>(customer.get(), HttpStatus.OK);
	}	
 
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/customers
     * HTTP method: POST
     * 
     */		
	@RequestMapping(value="/customers", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}
	
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/customers/customerId
     * HTTP method: PUT
     * 
     */	
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@PathVariable long customerId, 
    										@RequestBody Customer customer) {
    	Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
    	return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
	
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName/customers/customerId
     * HTTP method: DELETE
     * 
     */
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCustomer(@PathVariable long customerId) {
    	Optional<Customer> customer = customerService.getCustomer(customerId);
    	customerService.deleteCustomer(customer.get());
    	return new ResponseEntity<>(HttpStatus.OK);
    }
 
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName
     * HTTP method: GET
     * 
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
    	return new ResponseEntity<>("HFS REST API", HttpStatus.OK);
    }
  
}
