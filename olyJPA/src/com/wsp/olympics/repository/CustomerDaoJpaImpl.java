package com.wsp.olympics.repository;

import javax.persistence.EntityManager;

import com.wsp.olympics.model.Customer;

public class CustomerDaoJpaImpl extends DaoJpaImpl implements CustomerDao {
	public CustomerDaoJpaImpl(EntityManager em) {
		super(em);
	}

	@Override
	public String addCustomer(Customer customer) {
		em.persist(customer);
		return customer.getCustomerId();
	}

	@Override
	public Customer getCustomerById(String id) {
		return em.find(Customer.class, id);
	}
}
