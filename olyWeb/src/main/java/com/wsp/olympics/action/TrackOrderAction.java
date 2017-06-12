package com.wsp.olympics.action;

import com.wsp.olympics.model.Order;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.OrderService;
import com.wsp.olympics.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Controller for the order tracking action.
 * 
 * @author Patrick Altaie
 */
@Controller
public class TrackOrderAction {

	private OrderService orderService;
	private ShoppingCartService shoppingCartService;

	@Autowired
	public TrackOrderAction(OrderService orderService, ShoppingCartService shoppingCartService) {
		this.orderService = orderService;
		this.shoppingCartService = shoppingCartService;
	}

	@RequestMapping(value = "/track", method = RequestMethod.GET)
	public ModelAndView showTrack() {
		return new ModelAndView("track");
	}

	@RequestMapping(value = "/track", method = RequestMethod.POST)
	public ModelAndView doTrack(HttpServletRequest request,
								String orderNumber,
								@RequestParam(value = "surname", required = false) String surname)
			throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("track");
		if (orderNumber != null) {
			//If we've got an order number then look it up...
			Order order = orderService.getOrderByOrderNumber(orderNumber);
			if (order != null) {
				Long orderId = order.getOrderId();
				orderService.getOrderByOrderNumber(orderNumber);
				ShoppingCart cart = shoppingCartService.getCartByOrderId(orderId);
				modelAndView.addObject("cart", cart);
				if (cart != null) {
					//...but only if we can find the order...
					if (surname.toUpperCase().equals(cart.getOrder().getCustomer().getSurname().trim().toUpperCase())) {
						///...and the surname is right
						modelAndView.addObject("surnameMatch", true);
					} else {
						//Otherwise turn the user down
						modelAndView.addObject("surnameMatch", false);
					}
				} else {
					//If we can't find the cart (super unlikely) let the user know
					modelAndView.addObject("orderNotFound", true);
				}
			} else {
				//If we can't find the order let the user know
				modelAndView.addObject("orderNotFound", true);
			}
		}
		return modelAndView;
	}
}
