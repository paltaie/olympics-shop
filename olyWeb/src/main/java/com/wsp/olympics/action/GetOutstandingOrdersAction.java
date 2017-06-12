package com.wsp.olympics.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Gets a list of outstanding orders. This is an admin-only action.
 * 
 * @author Patrick Altaie
 */
@Controller
public class GetOutstandingOrdersAction {

	private ShoppingCartService cartService;

	@Autowired
	public GetOutstandingOrdersAction(ShoppingCartService cartService) {
		this.cartService = cartService;
	}

	@RequestMapping("admin/getOutstanding")
	public ModelAndView execute() {
		ModelAndView modelAndView = new ModelAndView("admin/getOutstanding");
		//Get a list of carts whose status is ordered or paid
		List<ShoppingCart> carts = cartService.getCartsByStatus(new String[] {"ORDERED", "PAID"});
		modelAndView.addObject("carts", carts);
		return modelAndView;
	}
}
