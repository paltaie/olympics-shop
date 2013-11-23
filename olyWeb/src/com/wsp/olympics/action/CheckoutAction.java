package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsp.olympics.model.Customer;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ShoppingCartService;

/**
 * Represents the checkout action. Handles various steps of the checkout process (e.g. initial details entry, confirmation page, etc).
 * 
 * @author Patrick Altaie
 */
public class CheckoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String confirmed = request.getParameter("confirmed");
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		//If the user's submitting their personal info
		if (request.getMethod().equals("POST")) {
			//Fill the DTO with the customer's info from the request and update the cart
			Customer customer = populateCustomer(cart.getOrder().getCustomer(), request);
			cart.getOrder().setCustomer(customer);
			session.setAttribute("cart", cart);
			request.setAttribute("cart", cart);
			request.getRequestDispatcher("/WEB-INF/jsp/confirmOrder.jsp").forward(request, response);
		} else {
			if (confirmed != null && !confirmed.isEmpty()) {
				//Submit the shopping cart for processing
				ShoppingCartService cartService = new ShoppingCartService();
				cart = cartService.submitShoppingCart(cart);
				if (cart == null) {
					//If we encountered some error, show a "failure" page
					request.getRequestDispatcher("/WEB-INF/jsp/orderFailure.jsp").forward(request, response);
				} else {
					//If it's all good, show a success page with the order number
					request.setAttribute("cart", cart);
					request.getRequestDispatcher("/WEB-INF/jsp/orderSuccess.jsp").forward(request, response);
					session.setAttribute("cart", null);
				}
			} else {
				//Otherwise this person is just starting the checkout process
				request.getRequestDispatcher("/WEB-INF/jsp/checkout.jsp").forward(request, response);
			}
		}
	}

	/**
	 * Populate a customer's info from the HTTP POST request we've received
	 * 
	 * @param customer the customer object to fill in
	 * @param request the request message from which we are extracting the info
	 * @return a populated @{link com.paltaie.olympics.model.Customer Customer} POJO (sans ID)
	 */
	private Customer populateCustomer(Customer customer, HttpServletRequest request) {
		customer.setSurname(request.getParameter("last_name"));
		customer.setGivenName(request.getParameter("first_name"));
		customer.setEmail(request.getParameter("email"));
		customer.setHouseNo(request.getParameter("house_no"));
		customer.setStreet(request.getParameter("street"));
		customer.setState(request.getParameter("state"));
		customer.setSuburb(request.getParameter("suburb"));
		customer.setPostcode(request.getParameter("postcode"));
		customer.setCountry(request.getParameter("country"));
		customer.setCcNumber(request.getParameter("cc"));
		return customer;
	}
}
