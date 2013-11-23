package com.wsp.olympics.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.wsp.olympics.model.Order;
import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;

/**
 * Session Bean implementation class ShoppingCartBean
 */
@Stateless(mappedName = "ejb/shoppingCart")
public class ShoppingCartBean implements ShoppingCartBeanRemote, ShoppingCartBeanLocal {

	@EJB(mappedName="ejb/order")
	private OrderBeanRemote orderBean;
	@EJB(mappedName="ejb/customer")
	private CustomerBeanRemote customerBean;
	@EJB(mappedName="ejb/orderProduct")
	private OrderProductBeanRemote orderProductBean;
	@EJB(mappedName="ejb/product")
	private ProductBeanRemote productBean;

	public ShoppingCartBean() {
		try {
			InitialContext ctx = new InitialContext();
			orderBean = (OrderBeanRemote) ctx.lookup("ejb/order#com.wsp.olympics.ejb.OrderBeanRemote");
			customerBean = (CustomerBeanRemote) ctx.lookup("ejb/customer#com.wsp.olympics.ejb.CustomerBeanRemote");
			orderProductBean = (OrderProductBeanRemote) ctx.lookup("ejb/orderProduct#com.wsp.olympics.ejb.OrderProductBeanRemote");
			productBean = (ProductBeanRemote) ctx.lookup("ejb/product#com.wsp.olympics.ejb.ProductBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ShoppingCart getCartByOrderId(String orderId) {
		try {
			ShoppingCart cart = new ShoppingCart();
			Order order = orderBean.getOrderById(orderId);
			List<OrderProduct> orderProducts = orderProductBean.getOrderProductsByOrderId(orderId);
			cart.setOrder(order);
			cart.setOrderProducts(orderProducts);
			return cart;
		} catch (NullPointerException e) {
			System.err.println("Couldn't find cart with order ID \"" + orderId + "\". Returning null...");
			return null;
		}
	}

	@Override
	public List<ShoppingCart> getCartsByStatus(String[] statuses) {
		List<ShoppingCart> carts = new ArrayList<ShoppingCart>();
		for (String status : statuses) {
			List<Order> orders = orderBean.getOrdersByStatus(status);
			for (Order order : orders) {
				ShoppingCart cart = getCartByOrderId(order.getOrderId());
				carts.add(cart);
			}
		}
		return carts;
	}

	@Override
	public ShoppingCart submitShoppingCart(ShoppingCart cart) {
		cart.getOrder().setStatus("ORDERED");
		customerBean.addCustomer(cart.getOrder().getCustomer());
		String orderId = orderBean.addOrder(cart.getOrder());
		Order order = orderBean.getOrderById(orderId);
		for (OrderProduct op : cart.getOrderProducts()) {
			op.setOrder(order);
			orderProductBean.addOrderProduct(op);
		}
		cart.setOrder(order);
		return cart;
	}
}
