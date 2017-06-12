package com.wsp.olympics.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wsp.olympics.model.Customer;
import com.wsp.olympics.model.Order;
import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;

public class CartUtils {
	/**
	 * Analyses the current cart situation and figures out if we need to create
	 * a new shopping cart, or use an existing one saved in the session
	 * 
	 * @param request the {{@link HttpServletRequest} whose session we are searching
	 * @return returns either a new shopping cart or the existing one saved in the session
	 */
	public ShoppingCart doCartLogic(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ShoppingCart cart;
		if (session.getAttribute("cart") == null) {
			//IF we don't have a cart in the session at all, initialise it
			cart = initCart();
			session.setAttribute("cart", cart);
		} else {
			//Otherwise just use the cart we have in the session
			cart = (ShoppingCart) session.getAttribute("cart");
		}
		return cart;
	}
	
	/**
	 * Creates an empty <code>ShoppingCart</code>
	 * 
	 * @return a new, empty shopping cart
	 */
	private ShoppingCart initCart() {
		ShoppingCart cart = new ShoppingCart();
		List<OrderProduct> orderProducts = new ArrayList<>();
		Customer customer = new Customer();
		Order order = new Order();
		order.setCustomer(customer);
		cart.setOrder(order);
		cart.setOrderProducts(orderProducts);
		return cart;
	}
}
