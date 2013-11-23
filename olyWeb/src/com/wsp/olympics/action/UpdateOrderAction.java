package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.OrderService;
import com.wsp.olympics.service.ShoppingCartService;

/**
 * Updates an order given the ID. This is an admin-only action
 * 
 * @author Patrick Altaie
 */
public class UpdateOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("order_id");
		String newOrderStatus = request.getParameter("new_status");
		ShoppingCartService cartService = new ShoppingCartService();
		if (newOrderStatus != null) {
			//Use the order service to fix up the order in the DB
			OrderService orderService = new OrderService();
			orderService.updateOrderStatus(orderId, newOrderStatus);
			request.setAttribute("statusUpdated", true);
		}
		//And then get the order back from the DB
		ShoppingCart cart = cartService.getCartByOrderId(orderId);
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateOrder.jsp").forward(request, response);
	}

}
