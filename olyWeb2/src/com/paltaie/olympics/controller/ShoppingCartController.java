package com.paltaie.olympics.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.paltaie.olympics.service.ProductService;
import com.paltaie.olympics.service.ShoppingCartService;
import com.wsp.olympics.model.Customer;
import com.wsp.olympics.model.Order;
import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.Product;
import com.wsp.olympics.model.ShoppingCart;

@Controller
@SessionAttributes({"cart"})
public class ShoppingCartController {

	private ProductService productService = new ProductService();
	
	@ModelAttribute("cart")
	public ShoppingCart createCart() {
		ShoppingCart cart = new ShoppingCart();
		List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		Customer customer = new Customer();
		Order order = new Order();
		order.setCustomer(customer);
		cart.setOrder(order);
		cart.setOrderProducts(orderProducts);
		return cart;
	}
	
	@RequestMapping(value="/cart.oly", method=RequestMethod.GET)
	public ModelAndView getCart(@ModelAttribute("cart") ShoppingCart cart) {
		ModelAndView mav = new ModelAndView("cart");
		mav.addObject("cart", cart);
		return mav;
	}
	
	@RequestMapping(value="/addToCart.oly", method=RequestMethod.POST)
	public ModelAndView addToCart(@ModelAttribute("cart") ShoppingCart cart, @RequestParam("product_code") String productCode, @RequestParam("qty") Integer qty) {
		ModelAndView mav = new ModelAndView("cart");
		Product product = productService.getProductByCode(productCode);
		if (productAlreadyInCart(product, cart)) {
			updateProductQuantity(product, qty, cart);
		} else {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setProduct(product);
			orderProduct.setQty(new BigDecimal(qty));
			cart.getOrderProducts().add(orderProduct);
		}
		return mav;
	}
	
	@RequestMapping(value="/updateCart.oly", method=RequestMethod.POST)
	public ModelAndView updateCart(@ModelAttribute("cart") ShoppingCart cart, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("cart");
		Enumeration<String> params = request.getParameterNames();
		//Break the process down into items to delete and things to update
		List<OrderProduct> deletes = new ArrayList<OrderProduct>();
		List<OrderProduct> updates = new ArrayList<OrderProduct>();

		//We could have used the ParameterMap here, but it's a bit more verbose and annoying
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			String value = request.getParameter(param);
			//If this parameter is relevant to the cart update
			if (param.startsWith("qty") || param.startsWith("remove_")) {
				String productCode = param.substring(param.indexOf("_") + 1, param.length());
				long productCodeLong = Long.parseLong(productCode);
				if (param.startsWith("remove_")) {
					//Search the cart for the product the user wants to remove
					for (OrderProduct op : cart.getOrderProducts()) {
						if (op.getProduct().getProductCode() == productCodeLong) {
							//Add it to the list of items to delete
							deletes.add(op);
						}
					}
				} else if (param.startsWith("qty_")) {
					for (OrderProduct op : cart.getOrderProducts()) {
						if (op.getProduct().getProductCode() == productCodeLong) {
							String qty = value;
							int qtyInt = Integer.parseInt(qty);
							//If the user wants to delete the item by setting its qty
							//to zero then just add it to the list of deletes
							if (qtyInt == 0) {
								deletes.add(op);
							//Otherwise update the quantity with whatever the quantity is
							} else {
								//The way this is done is by adding this OrderProduct to the
								//list of deletes and updates so that it's deleted and then
								//inserted again (stupid, but let's just roll with it)
								op.setQty(new BigDecimal(qtyInt));
								deletes.add(op);
								updates.add(op);
							}
						}
					}
				}
			}
		}
		//Update the cart with all quantities
		for (OrderProduct op : updates) {
			cart.getOrderProducts().add(op);
		}
		//Then delete the order lines don't need
		for (OrderProduct op : deletes) {
			cart.getOrderProducts().remove(op);
		}
		mav.addObject("cartUpdated", true);
		return mav;
	}

	@RequestMapping("/async/cartSize.oly")
	public ModelAndView getCartSize(@ModelAttribute("cart") ShoppingCart cart) {
		ModelAndView mav = new ModelAndView ("async/cartSize");
		if (cart == null) {
			mav.addObject("cartSize", "0");
		} else {
			int cartSize = cart.getOrderProducts().size();
			mav.addObject("cartSize", cartSize);
		}
		return mav;
	}

	@RequestMapping(value="/async/cartTable.oly")
	public ModelAndView getCartTable(@ModelAttribute("cart") ShoppingCart cart,
			@RequestParam(value="order_id", required=false) String orderId) {
		ModelAndView mav = new ModelAndView ("async/cartTable");
		if (orderId != null && !orderId.isEmpty()) {
			ShoppingCartService cartService = new ShoppingCartService();
			ShoppingCart resultCart = cartService.getCartByOrderId(orderId);
			mav.addObject("cart", resultCart);
		} else {
			mav.addObject("cart", cart);
		}
		return mav;
	}
	
	/**
	 * Searches the given shopping cart for <code>product</code>.
	 * 
	 * @param product the product to search for in the cart
	 * @param cart the cart to search
	 * @return true if the cart contains the product, false otherwise
	 */
	private boolean productAlreadyInCart(Product product, ShoppingCart cart) {
		for (OrderProduct op : cart.getOrderProducts()) {
			if (op.getProduct().getProductCode() == product.getProductCode()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Assuming that the cart contains this product (see <code>productAlreadyInCart</code>),
	 * add <code>quantity</code> more of this same product to the cart.
	 * 
	 * @param product the product to update
	 * @param quantity the additional items to add to the cart
	 * @param cart the shopping cart to update
	 */
	private void updateProductQuantity(Product product, int quantity, ShoppingCart cart) {
		for (OrderProduct op : cart.getOrderProducts()) {
			if (op.getProduct().getProductCode() == product.getProductCode()) {
				op.setQty(new BigDecimal(op.getQty().intValue() + quantity));
			}
		}
	}
}
