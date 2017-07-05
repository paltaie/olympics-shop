package com.wsp.olympics.action;

import com.wsp.olympics.model.Order;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.OrderService;
import com.wsp.olympics.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Updates an order given the ID. This is an admin-only action
 * 
 * @author Patrick Altaie
 */
@Controller
public class UpdateOrderAction {

	private OrderService orderService;
	private ShoppingCartService shoppingCartService;

	@Autowired
	public UpdateOrderAction(OrderService orderService, ShoppingCartService cartService) {
		this.orderService = orderService;
		this.shoppingCartService = cartService;
	}

	@RequestMapping("admin/updateOrder")
	public ModelAndView execute(@RequestParam("order_number") String orderNumber,
								@RequestParam(value = "new_status", required = false) String newOrderStatus) {
		ModelAndView modelAndView = new ModelAndView("admin/updateOrder");
		Long orderId;
		Order order = orderService.getOrderByOrderNumber(orderNumber);
		if (order != null) {
			orderId = order.getOrderId();
			if (newOrderStatus != null) {
				//Use the order service to fix up the order in the DB
				orderService.updateOrderStatus(orderId, newOrderStatus);
				modelAndView.addObject("statusUpdated", true);
			}
			//And then get the order back from the DB
			ShoppingCart cart = shoppingCartService.getCartByOrderId(orderId);
			modelAndView.addObject("cart", cart);
		}
		return modelAndView;
	}
}
