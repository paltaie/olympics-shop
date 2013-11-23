package com.wsp.olympics.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.wsp.olympics.model.Order;

@Remote
public interface OrderBeanRemote {
	/**
	 * Adds this order to the data source
	 * 
	 * @param order the order to add
	 * @return the order ID
	 */
	public String addOrder(Order order);
	/**
	 * Retrieves an order by its ID, or null if it cannot be found.
	 * 
	 * @param orderId the ID to search for
	 * @return the order that was found, or null
	 */
	public Order getOrderById(String orderId);
	/**
	 * Gets an order by its order number (i.e. the business ID), or null if it cannot be found.
	 * 
	 * @param orderNumber the order number to search for
	 * @return the order that was found, or null
	 */
	public Order getOrderByOrderNumber(String orderNumber);
	/**
	 * Gets a list of orders based on the given order status.
	 * 
	 * @param status the status(es) to look for
	 * @return a list of orders with those statuses
	 */
	public List<Order> getOrdersByStatus(String status);
	/**
	 * Updates an order status with the given status
	 * 
	 * @param orderId the order to update
	 * @param status the status to set
	 */
	public void updateOrderStatus(String orderId, String status);
}
