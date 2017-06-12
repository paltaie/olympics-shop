package com.wsp.olympics.ws.types;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.wsp.olympics.model.Order;
import com.wsp.olympics.model.OrderProduct;

@XmlType(namespace="http://types.ws.olympics.wsp.com/")
public class PaidOrder {
	private Order order;
	private List<OrderProduct> lineItem;

	public PaidOrder(Order order, List<OrderProduct> lineItem) {
		this.order = order;
		this.lineItem = lineItem;
	}

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
	 * @return the lineItem
	 */
	public List<OrderProduct> getLineItem() {
		return lineItem;
	}
	/**
	 * @param lineItem the lineItem to set
	 */
	public void setLineItem(List<OrderProduct> lineItem) {
		this.lineItem = lineItem;
	}
}
