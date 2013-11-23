package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.utils.CartUtils;

/**
 * Various cart-related actions such as updating the cart, adding items, etc.
 * 
 * @author Patrick Altaie
 */
public class ViewCartAction implements Action {
	private ShoppingCart cart = null;
	private CartUtils cartUtils = new CartUtils();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		cart = cartUtils.doCartLogic(request);
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
	}
}
