package com.wsp.olympics.action;

import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.Product;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ProductService;
import com.wsp.olympics.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@Controller
public class AddToCartAction {

	private ShoppingCart cart = null;
	private CartUtils cartUtils;
	private ProductService productService;

	@Autowired
	public AddToCartAction(CartUtils cartUtils, ProductService productService) {
		this.cartUtils = cartUtils;
		this.productService = productService;
	}

	@RequestMapping("/addToCart")
	public ModelAndView execute(HttpServletRequest request,
								HttpSession session,
								@RequestParam("product_code") String productId,
								@RequestParam("qty") String quantity)
			throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("cart");
		cart = cartUtils.doCartLogic(request);
		addItem(session, productService, quantity, productId);
		modelAndView.addObject("cart", cart);
		return modelAndView;
	}
	
	/**
	 * Adds a single item to the session's shopping cart
	 * 
	 * @param session the <code>HttpServletRequest</code> we're getting the info from
	 * @param productService the service we're using to look up product info
	 * and populate into the session
	 */
	private void addItem(HttpSession session,
						 ProductService productService,
						 String quantity,
						 String productId) {
		int quantityInt = Integer.parseInt(quantity);
		Product product = productService.getProductByCode(Long.valueOf(productId));
		if (productAlreadyInCart(product, cart)) {
			updateProductQuantity(product, quantityInt, cart);
		} else {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setProduct(product);
			orderProduct.setQty(new BigDecimal(quantityInt));
			cart.getOrderProducts().add(orderProduct);
		}
		session.setAttribute("cart", cart);
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
