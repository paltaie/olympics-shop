package com.wsp.olympics.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ShoppingCartService;

/**
 * Gets a list of outstanding orders. This is an admin-only action.
 * 
 * @author Patrick Altaie
 */
public class GetOutstandingOrdersAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Get a list of carts whose status is ordered or paid
		ShoppingCartService cartService = new ShoppingCartService();
		List<ShoppingCart> carts = cartService.getCartsByStatus(new String[] {"ORDERED", "PAID"});
		request.setAttribute("carts", carts);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/getOutstanding.jsp").forward(request, response);
	}
}
