package com.wsp.olympics.repository;

import java.util.List;

import com.wsp.olympics.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Performs various order-related operations
 * 
 * @author WSP
 */
@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
	/**
	 * Gets an order by its order number (i.e. the business ID), or null if it cannot be found.
	 * 
	 * @param orderNumber the order number to search for
	 * @return the order that was found, or null
	 */
	public Order findByOrderNumber(String orderNumber);
	/**
	 * Gets a list of orders based on the given order status.
	 * 
	 * @param status the status(es) to look for
	 * @return a list of orders with those statuses
	 */
	public List<Order> findByStatus(String status);
}
