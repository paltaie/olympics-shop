package com.wsp.supplierapp.service;

import com.wsp.olympics.ws.types.PaidOrder;
import com.wsp.supplierapp.view.Observer;

import java.util.List;
import java.util.Properties;

public class SupplierModel extends Observer {
	private OrderService orderService;
	private List<PaidOrder> paidOrderList;
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
	public List<PaidOrder> getPaidOrderList() {
		return paidOrderList;
	}

	/**
	 * @param paidOrderList the paidOrderList to set
	 */
	public void setPaidOrderList(List<PaidOrder> paidOrderList) {
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
