package com.wsp.olympics.service;

import com.wsp.olympics.model.Order;
import com.wsp.olympics.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provides services for retrieving orders
 * 
 * @author Patrick Altaie
 */
@Component
public class OrderService {
	private OrderDao orderDao;

	@Autowired
	public OrderService(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * Updates the status of the order specified by that ID.
	 * 
	 * @param orderId the order to update
	 * @param status the new status to set
	 */
	public void updateOrderStatus(Long orderId, String status) {
		Order order = orderDao.getById(orderId);
		order.setStatus(status);
		orderDao.save(order);
	}

	/**
	 * Gets an order by its order number (i.e. the business ID), or null if it cannot be found.
	 * 
	 * @param orderNumber the order number to search for
	 * @return the order that was found, or null
	 */
	public Order getOrderByOrderNumber(String orderNumber) {
		return orderDao.findByOrderNumber(orderNumber);
	}
}
