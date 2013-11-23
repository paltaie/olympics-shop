package com.paltaie.olympics.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paltaie.olympics.service.OrderService;
import com.paltaie.olympics.service.ShoppingCartService;
import com.wsp.olympics.model.ShoppingCart;

@Controller
public class OrderController {
	private OrderService orderService;
	private ShoppingCartService cartService;
	
	public OrderController() {
		orderService = new OrderService();
		cartService = new ShoppingCartService();
	}
	
	@RequestMapping(value="/track.oly", method=RequestMethod.GET)
	public ModelAndView trackOrder() {
		return new ModelAndView("track", "model", null);
	}
	
	@RequestMapping(value="/track.oly", method=RequestMethod.POST)
	public ModelAndView trackOrder(@RequestParam("order_id") String orderId, @RequestParam("surname") String surname) {
		ShoppingCart cart = cartService.getCartByOrderId(orderId);
		Map<String, Object> model = new Hashtable<String, Object>();
		if (cart.getOrder() == null) {
			model.put("orderNotFound", true);
		} else {
			if (cart.getOrder().getCustomer().getSurname().trim().toUpperCase().equals(surname.trim().toUpperCase())) {
				model.put("surnameMatch", true);
			} else {
				model.put("surnameMatch", false);
			}
		}
		model.put("cart", cart);
		return new ModelAndView("track", "model", model);
	}
	
	@RequestMapping("/admin/getOutstanding.oly")
	public ModelAndView getOutstandingOrders() {
		ModelAndView mav = new ModelAndView("admin/getOutstanding");
		List<ShoppingCart> carts = cartService.getCartsByStatus(new String[] {"ORDERED", "PAID"});
		mav.addObject("carts", carts);
		return mav;
	}
	
	@RequestMapping("/admin/updateOrder.oly")
	public ModelAndView viewOrder(@RequestParam("order_id") String orderId, @RequestParam(value="new_status", required=false) String newOrderStatus) {
		ModelAndView mav = new ModelAndView("admin/updateOrder");
		if (newOrderStatus != null && !newOrderStatus.isEmpty()) {
			orderService.updateOrderStatus(orderId, newOrderStatus);
			mav.addObject("statusUpdated", true);
		}
		ShoppingCart cart = cartService.getCartByOrderId(orderId);
		mav.addObject("cart", cart);
		return mav;
	}
}
