package com.sds.demo.dao;

import java.util.List;

import com.sds.demo.entity.Customer;

public interface CustomerDao {
	
	public List<Customer> getallCustomers();
    
	public Customer getCustomer(int customerId);
	
	public void saveCustomer(Customer customer);
	
	public void deleteCustomer(int customerId);

}
