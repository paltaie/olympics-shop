package com.paltaie.olympics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.paltaie.olympics.service.ShoppingCartService;
import com.wsp.olympics.model.Customer;
import com.wsp.olympics.model.ShoppingCart;

@Controller
@SessionAttributes({"cart"})
public class CheckoutController {
	private ShoppingCartService cartService = new ShoppingCartService();
	
	@RequestMapping(value="/checkout.oly", method=RequestMethod.GET)
	public ModelAndView getCustomerInfo() {
		return new ModelAndView("checkout");
	}
	
	@RequestMapping(value="/checkout.oly", method=RequestMethod.POST)
	public ModelAndView doCheckout(@ModelAttribute("customer") Customer customer, @ModelAttribute("cart") ShoppingCart cart, BindingResult result) {
		cart.getOrder().setCustomer(customer);
		return new ModelAndView("confirmOrder");
	}
	
	@RequestMapping(value="/checkout.oly", params={"confirmed=true"}, method=RequestMethod.GET)
	public ModelAndView submitCart(@ModelAttribute("cart") ShoppingCart cart) {
		ModelAndView mav = new ModelAndView("orderSuccess");
		try {
			cart = cartService.submitShoppingCart(cart);
			mav.addObject("orderId", cart.getOrder().getOrderId());
		} catch (Exception e) {
			mav.setViewName("orderFailure");
		}
		return mav;
	}
}
