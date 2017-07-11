package com.wsp.olympics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wsp.olympics.model.Customer;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Represents the checkout action. Handles various steps of the checkout process (e.g. initial details entry, confirmation page, etc).
 * 
 * @author Patrick Altaie
 */
@Controller
public class CheckoutAction {

	private ShoppingCartService cartService;

	@Autowired
	public CheckoutAction(ShoppingCartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping(value = "/checkout")
	public ModelAndView execute(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("checkout");
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		Customer customer = populateCustomer(cart.getOrder().getCustomer(), request);
		cart.getOrder().setCustomer(customer);
		session.setAttribute("cart", cart);
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("confirmOrder");
		return modelAndView;
	}

	@GetMapping("/checkout")
	public ModelAndView doCheckout(HttpServletRequest request, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("checkout");
		String confirmed = request.getParameter("confirmed");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (confirmed != null && !confirmed.isEmpty()) {
			//Submit the shopping cart for processing
			cart = cartService.submitShoppingCart(cart);
			if (cart == null) {
				//If we encountered some error, show a "failure" page
				modelAndView.setViewName("orderFailure");
			} else {
				//If it's all good, show a success page with the order number
				modelAndView.addObject("cart", cart);
				session.setAttribute("cart", null);
				modelAndView.setViewName("orderSuccess");
			}
		}
		return modelAndView;
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
