package com.wsp.olympics.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.wsp.olympics.ejb.OrderBeanRemote;
import com.wsp.olympics.ejb.ShoppingCartBeanRemote;
import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.ws.types.PaidOrder;

@WebService
public class Order {

	@EJB
	private OrderBeanRemote orderBean;
	@EJB
	private ShoppingCartBeanRemote shoppingCartBean;
	
	public Order() {
		try {
			InitialContext ctx = new InitialContext();
			orderBean = (OrderBeanRemote) ctx.lookup("ejb/order#com.wsp.olympics.ejb.OrderBeanRemote");
			shoppingCartBean = (ShoppingCartBeanRemote) ctx.lookup("ejb/shoppingCart#com.wsp.olympics.ejb.ShoppingCartBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@WebMethod
	public void updateOrderStatus(@WebParam(name="orderId") String orderId, @WebParam(name="status") String status) {
		if (status.toUpperCase().trim().equals("SENT")) {
			try {
				orderBean.updateOrderStatus(orderId, status);
			} catch (Exception e) {
				if (e.getCause() instanceof NullPointerException) {
					throw new RuntimeException("Can't find order with ID \"" + orderId + "\"");
				}
			}
		} else {
			throw new IllegalArgumentException("You can only set the status of an order to \"SENT\"");
		}
	}
	
	@WebMethod
	@WebResult(name="cart")
	public List<PaidOrder> getPaidOrders() {
		List<PaidOrder> responses = new ArrayList<PaidOrder>();
		List<ShoppingCart> carts = shoppingCartBean.getCartsByStatus(new String[] {"PAID"});
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
