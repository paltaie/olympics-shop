package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.utils.CartUtils;

public class ClearCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartUtils utils = new CartUtils();
		ShoppingCart cart = utils.doCartLogic(request);
		cart.getOrderProducts().clear();
		request.setAttribute("cartCleared", true);
		request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
	}

}
