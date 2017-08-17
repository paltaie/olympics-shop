package com.wsp.olympics.supplierapp.service;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.supplierapp.view.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplierModel extends Observer {
	private OrderService orderService;
	private List<ShoppingCart> paidOrderList;

	@Autowired
    public SupplierModel(OrderService orderService) {
        this.orderService = orderService;
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
}
