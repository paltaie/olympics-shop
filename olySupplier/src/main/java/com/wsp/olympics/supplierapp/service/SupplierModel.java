package com.wsp.olympics.supplierapp.service;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.supplierapp.view.Observer;

import java.util.List;
import java.util.Properties;

public class SupplierModel extends Observer {
	private OrderService orderService;
	private List<ShoppingCart> paidOrderList;
	private Properties props;
	
	public SupplierModel(Properties props) {
		this.props = props;
		orderService = new OrderService(props);
	}

	/**
	 * @return the orderService
	 */
	public OrderService getOrderService() {
		return orderService;
	}

	/**
	 * @param orderService the orderService to set
	 */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * @return the paidOrderList
	 */
	public List<ShoppingCart> getPaidOrderList() {
		return paidOrderList;
	}

	/**
	 * @param paidOrderList the paidOrderList to set
	 */
	public void setPaidOrderList(List<ShoppingCart> paidOrderList) {
		this.paidOrderList = paidOrderList;
	}

	/**
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}

	/**
	 * @param props the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}
}
