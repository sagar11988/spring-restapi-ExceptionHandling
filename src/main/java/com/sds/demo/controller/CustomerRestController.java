package com.sds.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.demo.entity.Customer;
import com.sds.demo.exception.CustomerNotFoundException;
import com.sds.demo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	
	@Autowired
	CustomerService customerservice;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		
		return customerservice.getallCustomers();
	}
	
	
	@GetMapping("/customers/{customerid}")
	public Customer getCustomer(@PathVariable int customerid){
		
		Customer customer=customerservice.getCustomer(customerid);
		
		Optional<Customer> isNull=Optional.ofNullable(customer);
		
		if(isNull.isEmpty()) {
			
			throw new CustomerNotFoundException("Customer Not Found ="+customerid);
		}
		
		return customer;
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		
		customer.setId(0);
		
		customerservice.saveCustomer(customer);
		
		return customer;
	}
	
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		
		//customer.setId(0);
		
		customerservice.saveCustomer(customer);
		
		return customer;
	}
	
	

	@DeleteMapping("/customers/{customerid}")
	public String deleteCustomer(@PathVariable int customerid){
		
		Customer customer=customerservice.getCustomer(customerid);
		
		Optional<Customer> isNull=Optional.ofNullable(customer);
		
		if(isNull.isEmpty()) {
			
			throw new CustomerNotFoundException("Customer Not Found ="+customerid);
		}
		
		customerservice.deleteCustomer(customerid);
		
		return "Deleted the Customer with id-"+customerid;
	}
	
	

}
