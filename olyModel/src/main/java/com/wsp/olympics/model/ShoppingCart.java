package com.wsp.olympics.model;

import java.awt.Desktop.Action;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.management.openmbean.OpenDataException;

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
		return orderProducts.stream().map(orderProducts ->
				orderProducts.getProduct().getPrice().multiply(orderProducts.getQty()))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
