package com.wsp.olympics.ws;

import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.OrderService;
import com.wsp.olympics.service.ShoppingCartService;
import com.wsp.olympics.ws.types.PaidOrder;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class Order {

	private OrderService orderService;
	private ShoppingCartService shoppingCartService;

	@WebMethod
	public void updateOrderStatus(@WebParam(name="orderNumber") String orderNumber, @WebParam(name="status") String status) {
		com.wsp.olympics.model.Order order = orderService.getOrderByOrderNumber(orderNumber);
		if (order == null) {
			throw new RuntimeException("Can't find order with ID \"" + orderNumber + "\"");
		}
		Long orderId = order.getOrderId();
		if (status.toUpperCase().trim().equals("SENT")) {
			orderService.updateOrderStatus(orderId, status);
		} else {
			throw new IllegalArgumentException("You can only set the status of an order to \"SENT\"");
		}
	}
	
	@WebMethod
	@WebResult(name="cart")
	public List<PaidOrder> getPaidOrders() {
		List<PaidOrder> responses = new ArrayList<PaidOrder>();
		List<ShoppingCart> carts = shoppingCartService.getCartsByStatus(new String[] {"PAID"});
		for (ShoppingCart cart : carts) {
			PaidOrder response = new PaidOrder();
			List<OrderProduct> ops = new ArrayList<OrderProduct>();
			response.setOrder(cart.getOrder());
			for (OrderProduct op : cart.getOrderProducts()) {
				ops.add(op);
			}
			response.setLineItem(ops);
			responses.add(response);
		}
		return responses;
	}
}
