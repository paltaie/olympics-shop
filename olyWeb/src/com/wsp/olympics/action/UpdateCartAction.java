package com.wsp.olympics.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.utils.CartUtils;

public class UpdateCartAction implements Action {

	private ShoppingCart cart = null;
	private CartUtils cartUtils = new CartUtils();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		cart = cartUtils.doCartLogic(request);
		updateCart(request);
		request.setAttribute("cart", cart);
		request.getSession().setAttribute("cart", cart);
		request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);
	}
	
	/**
	 * Updates the session's cart with quantity updates and deletions
	 * 
	 * @param request the request whose session we're changing, and whose parameters
	 * we need to use to update the cart
	 */
	private void updateCart(HttpServletRequest request) {
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
		request.setAttribute("cartUpdated", true);
	}

}
