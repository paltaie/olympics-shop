package com.wsp.olympics.action;

import javax.servlet.http.HttpServletRequest;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Various cart-related actions such as updating the cart, adding items, etc.
 * 
 * @author Patrick Altaie
 */
@Controller
public class ViewCartAction {
	private CartUtils cartUtils;

	@Autowired
	public ViewCartAction(CartUtils cartUtils) {
		this.cartUtils = cartUtils;
	}

	@RequestMapping("/cart")
	public ModelAndView execute(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("cart");
		ShoppingCart cart = cartUtils.doCartLogic(request);
		modelAndView.addObject("cart", cart);
		return modelAndView;
	}
}
