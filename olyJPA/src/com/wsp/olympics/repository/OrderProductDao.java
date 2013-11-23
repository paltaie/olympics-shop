package com.wsp.olympics.repository;

import java.util.List;

import com.wsp.olympics.model.OrderProduct;

/**
 * Performs various operations on OrderProducts (an order/product linking)
 * 
 * @author WSP
 */
public interface OrderProductDao {
	/**
	 * Adds an order/product link to the data source
	 * 
	 * @param op the order/product to add
	 */
	public void addOrderProduct(OrderProduct op);
	/**
	 * Retrieves an order/product correlation by the master order ID (one order has multiple order-product linkings)
	 * 
	 * @param orderId the order ID to look for
	 * @return the list of order/products to get
	 */
	public List<OrderProduct> getOrderProductsByOrderId(String orderId);
}
