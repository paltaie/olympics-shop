package com.wsp.olympics.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.wsp.olympics.ejb.OrderBeanRemote;
import com.wsp.olympics.model.Order;

/**
 * Provides services for retrieving orders
 * 
 * @author Patrick Altaie
 */
public class OrderService {
	private OrderBeanRemote orderBean;
	
	/**
	 * Instantiates the orderDao
	 */
	public OrderService() {
		try {
			InitialContext ctx = new InitialContext();
			orderBean = (OrderBeanRemote) ctx.lookup("ejb/order#com.wsp.olympics.ejb.OrderBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the status of the order specified by that ID.
	 * 
	 * @param orderId the order to update
	 * @param status the new status to set
	 */
	public void updateOrderStatus(String orderId, String status) {
		orderBean.updateOrderStatus(orderId, status);
	}
	

	/**
	 * Gets an order by its order number (i.e. the business ID), or null if it cannot be found.
	 * 
	 * @param orderNumber the order number to search for
	 * @return the order that was found, or null
	 */
	public Order getOrderByOrderNumber(String orderNumber) {
		return orderBean.getOrderByOrderNumber(orderNumber);
	}
}
