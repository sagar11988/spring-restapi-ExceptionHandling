package com.sds.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sds.demo.dao.CustomerDao;
import com.sds.demo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	@Qualifier("customerdao")
	private CustomerDao customerDao;
	
	@Override
	@Transactional
	public List<Customer> getallCustomers() {
		
		List<Customer> customers=customerDao.getallCustomers();
		
		return customers;
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {

       
		return customerDao.getCustomer(customerId);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		
		customerDao.saveCustomer(customer);
		

	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {


		customerDao.deleteCustomer(customerId);

	}

}
