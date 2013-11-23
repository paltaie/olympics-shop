package com.wsp.olympics.action;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.Product;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ProductService;
import com.wsp.olympics.utils.CartUtils;

public class AddToCartAction implements Action {

	private ShoppingCart cart = null;
	private CartUtils cartUtils = new CartUtils();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService productService = new ProductService();
		cart = cartUtils.doCartLogic(request);
		addItem(request, productService);
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
	}
	
	/**
	 * Adds a single item to the session's shopping cart
	 * 
	 * @param request the <code>HttpServletRequest</code> we're getting the info from
	 * @param productService the service we're using to look up product info
	 * and populate into the session
	 */
	private void addItem(HttpServletRequest request, ProductService productService) {
		HttpSession session = request.getSession();
		String productId = request.getParameter("product_code");
		String quantity = request.getParameter("qty");
		int quantityInt = Integer.parseInt(quantity);
		Product product = productService.getProductByCode(productId);
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
