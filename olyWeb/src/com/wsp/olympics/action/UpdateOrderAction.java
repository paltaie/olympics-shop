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
 * Updates an order given the ID. This is an admin-only action
 * 
 * @author Patrick Altaie
 */
public class UpdateOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderNumber = request.getParameter("order_number");
		String newOrderStatus = request.getParameter("new_status");
		String orderId;
		OrderService orderService = new OrderService();
		ShoppingCartService cartService = new ShoppingCartService();
		Order order = orderService.getOrderByOrderNumber(orderNumber);
		if (order != null) {
			orderId = order.getOrderId();
			if (newOrderStatus != null) {
				//Use the order service to fix up the order in the DB
				orderService.updateOrderStatus(orderId, newOrderStatus);
				request.setAttribute("statusUpdated", true);
			}
			//And then get the order back from the DB
			ShoppingCart cart = cartService.getCartByOrderId(orderId);
			request.setAttribute("cart", cart);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateOrder.jsp").forward(request, response);
	}

}
