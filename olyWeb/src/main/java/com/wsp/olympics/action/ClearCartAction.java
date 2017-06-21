package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClearCartAction {

	private CartUtils cartUtils;

	@Autowired
	public ClearCartAction(CartUtils cartUtils) {
		this.cartUtils = cartUtils;
	}

	@RequestMapping("/clearCart")
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("cart");
		ShoppingCart cart = cartUtils.doCartLogic(request);
		cart.getOrderProducts().clear();
		modelAndView.addObject("cartCleared", true);
		return modelAndView;
	}

}
