package com.wsp.olympics.action;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClearCartAction {

	private CartUtils cartUtils;

	@Autowired
	public ClearCartAction(CartUtils cartUtils) {
		this.cartUtils = cartUtils;
	}

	@GetMapping("/clearCart")
	public ModelAndView execute(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("cart");
		ShoppingCart cart = cartUtils.doCartLogic(request);
		cart.getOrderProducts().clear();
		modelAndView.addObject("cartCleared", true);
		return modelAndView;
	}

}
