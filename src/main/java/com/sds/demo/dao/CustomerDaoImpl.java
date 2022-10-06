package com.sds.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sds.demo.entity.Customer;

@Repository("customerdao")
public class CustomerDaoImpl implements CustomerDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getallCustomers() {
           
		Session session=sessionFactory.getCurrentSession();
		
		Query<Customer> qurey=session.createQuery("from Customer order by lastName",Customer.class);
		
		List<Customer> customers=qurey.getResultList();

		return customers;
	}

	@Override
	public Customer getCustomer(int customerId) {
		
		Session session=sessionFactory.getCurrentSession();	
		
		
		return session.get(Customer.class, customerId);
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session session=sessionFactory.getCurrentSession();
		
		 session.saveOrUpdate(customer);
		
	}

	@Override
	public void deleteCustomer(int customerId) {
		
		Session session=sessionFactory.getCurrentSession();
		
		Query<?> query=session.createQuery("delete from Customer where id=:id");
		
		query.setParameter("id", customerId);
		
		query.executeUpdate();
	}

}
