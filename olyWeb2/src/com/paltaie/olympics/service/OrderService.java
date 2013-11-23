package com.paltaie.olympics.service;

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
	
	public Order getOrderById(String orderId) {
		return orderBean.getOrderById(orderId);
	}
}
