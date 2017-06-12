package com.wsp.olympics.service;

import com.wsp.olympics.model.Customer;
import com.wsp.olympics.model.Order;
import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.repository.OrderDao;
import com.wsp.olympics.repository.OrderProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Performs various shopping cart-related operations by utilising various DAOs to construct the data model
 * 
 * @author Patrick Altaie
 */
@Component
public class ShoppingCartService {

	private OrderDao orderDao;
	private OrderProductDao orderProductDao;

	@Autowired
	public ShoppingCartService(OrderDao orderDao, OrderProductDao orderProductDao) {
		this.orderDao = orderDao;
		this.orderProductDao = orderProductDao;
	}

	/**
	 * Gets a shopping cart based on the given order ID.
	 * 
	 * @param orderId the ID to search for
	 * @return the shopping cart for that ID, or null if the cart cannot be found.
	 */
	public ShoppingCart getCartByOrderId(Long orderId) {
		try {
			ShoppingCart cart = new ShoppingCart();
			Order order = orderDao.findOne(orderId);
			List<OrderProduct> orderProducts = orderProductDao.findByOrderOrderId(orderId);
			cart.setOrder(order);
			cart.setOrderProducts(orderProducts);
			return cart;
		} catch (NullPointerException e) {
			System.err.println("Couldn't find cart with order ID \"" + orderId + "\". Returning null...");
			return null;
		}
	}

	/**
	 * Retrieves a list of shopping carts by their order status
	 * 
	 * @param statuses a list of statuses to search for
	 * @return a list of shopping carts with those particular statuses (or an empty cart if none could be found)
	 */
	public List<ShoppingCart> getCartsByStatus(String[] statuses) {
        return orderDao.findByStatusIn(Arrays.asList(statuses))
                .stream()
                .map(order -> getCartByOrderId(order.getOrderId()))
                .collect(Collectors.toList());
	}

	/**
	 * Submits a shopping cart for insertion into the data source
	 * 
	 * @param cart the cart to insert to the DB
	 * @return an updated ShoppingCart with IDs and statuses that have been returned from the data source
	 */
	@Transactional
	public ShoppingCart submitShoppingCart(ShoppingCart cart) {
		Order order = cart.getOrder();
		Customer customer = order.getCustomer();
		order.setStatus("PAID");
		order.setCustomer(customer);
		order.setOrderNumber(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
		order = orderDao.save(order);
		for (OrderProduct op : cart.getOrderProducts()) {
			op.setOrder(order);
			orderProductDao.save(op);
		}
		cart.setOrder(order);
		return cart;
	}
}
