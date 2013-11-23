package com.wsp.olympics.repository;

import com.wsp.olympics.model.Customer;

/**
 * Performs various customer-related operations
 * 
 * @author WSP
 */
public interface CustomerDao {
	/**
	 * Adds a customer object to the data source
	 * 
	 * @param customer the customer to add
	 * @return the ID for the customer that was just added
	 */
	public String addCustomer(Customer customer);
	/**
	 * Retrieves a customer's details given their customer ID.
	 * 
	 * @param id the ID of the customer to search for
	 * @return the customer's deatils
	 */
	public Customer getCustomerById(String id);
}
