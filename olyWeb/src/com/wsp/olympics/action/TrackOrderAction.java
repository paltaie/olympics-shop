package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.Order;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.OrderService;
import com.wsp.olympics.service.ShoppingCartService;

/**
 * Controller for the order tracking action.
 * 
 * @author Patrick Altaie
 */
public class TrackOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equals("POST")) {
			String orderNumber = request.getParameter("order_number").trim();
			OrderService orderService = new OrderService();
			if (orderNumber != null) {
				//If we've got an order number then look it up...
				Order order = orderService.getOrderByOrderNumber(orderNumber);
				if (order != null) {
					String orderId = order.getOrderId();
					ShoppingCartService service = new ShoppingCartService();
					orderService.getOrderByOrderNumber(orderNumber);
					ShoppingCart cart = service.getCartByOrderId(orderId);
					String surname = request.getParameter("surname").trim();
					request.setAttribute("cart", cart);
					if (cart != null) {
						//...but only if we can find the order...
						if (surname.toUpperCase().equals(cart.getOrder().getCustomer().getSurname().trim().toUpperCase())) {
							///...and the surname is right
							request.setAttribute("surnameMatch", true);
						} else {
							//Otherwise turn the user down
							request.setAttribute("surnameMatch", false);
						}
					} else {
						//If we can't find the cart (super unlikely) let the user know
						request.setAttribute("orderNotFound", true);
					}
				} else {
					//If we can't find the order let the user know
					request.setAttribute("orderNotFound", true);
				}
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/track.jsp").forward(request, response);
	}
}
