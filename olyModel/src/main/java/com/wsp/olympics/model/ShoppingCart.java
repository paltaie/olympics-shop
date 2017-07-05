package com.wsp.olympics.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a shopping cart - an order and a list of items and their quantities
 * 
 * @author Patrick Altaie
 */
public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;
	private Order order;
	private List<OrderProduct> orderProducts;
	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	/**
	 * @return the orderProducts
	 */
	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	/**
	 * @param orderProducts the orderProducts to set
	 */
	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BigDecimal getOrderTotal() {
		return orderProducts.stream().map(orderProduct ->
				orderProduct.getProduct().getPrice().multiply(orderProduct.getQty()))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public int getTotalItems() {
		return getOrderProducts()
				.stream()
				.mapToInt(lineItem -> lineItem.getQty().intValue())
				.sum();
	}
}
