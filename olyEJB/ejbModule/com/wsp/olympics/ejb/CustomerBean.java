package com.wsp.olympics.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wsp.olympics.model.Customer;
import com.wsp.olympics.repository.CustomerDao;
import com.wsp.olympics.repository.CustomerDaoJpaImpl;

/**
 * Session Bean implementation class CustomerBean
 */
@Stateless(mappedName = "ejb/customer")
public class CustomerBean implements CustomerBeanRemote, CustomerBeanLocal {

	@PersistenceContext(unitName="olyJPA")
	private EntityManager em;
	private CustomerDao dao;
	
	@Override
	public String addCustomer(Customer customer) {
		return dao.addCustomer(customer);
	}

	@Override
	public Customer getCustomerById(String id) {
		return dao.getCustomerById(id);
	}

	@PostConstruct
	private void initDao() {
		if (dao == null) {
			dao = new CustomerDaoJpaImpl(em);
		}
	}
}
