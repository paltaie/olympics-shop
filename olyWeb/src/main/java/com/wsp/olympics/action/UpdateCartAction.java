package com.wsp.olympics.action;

import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.utils.CartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class UpdateCartAction {

	private ShoppingCart cart = null;
	private CartUtils cartUtils;

	@Autowired
    public UpdateCartAction(CartUtils cartUtils) {
        this.cartUtils = cartUtils;
    }

    @RequestMapping("/updateCart")
	public ModelAndView execute(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("cart");
		cart = cartUtils.doCartLogic(request);
		updateCart(request, modelAndView);
		modelAndView.addObject("cart", cart);
		request.getSession().setAttribute("cart", cart);
		return modelAndView;
	}
	
	/**
	 * Updates the session's cart with quantity updates and deletions
	 * 
	 * @param request the request whose session we're changing, and whose parameters
	 * we need to use to update the cart
	 */
	private void updateCart(HttpServletRequest request, ModelAndView modelAndView) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		//Break the process down into items to delete and things to update
		List<OrderProduct> deletes = new ArrayList<>();
		List<OrderProduct> updates = new ArrayList<>();

		String[] productIdsToRemove = request.getParameterValues("remove");

		if (productIdsToRemove != null) {
            Arrays.stream(productIdsToRemove)
                    .forEach(productId ->
                            cart.getOrderProducts()
                                .removeIf(op -> op.getProduct().getProductCode() == Long.valueOf(productId)));
        }

        parameterMap.entrySet().stream().filter(entry -> entry.getKey().startsWith("qty_"))
            .forEach(entry -> {
                Long newQty = Long.valueOf(entry.getValue()[0]);
                Long productCode = Long.valueOf(entry.getKey().substring(entry.getKey().indexOf('_') + 1,
                        entry.getKey().length()));
                cart.getOrderProducts().forEach(op -> {
                        if (op.getProduct().getProductCode() == productCode) {
                            if (newQty == 0) {
                                //If the user wants to delete the item by setting its qty
                                //to zero then just add it to the list of deletes
                                deletes.add(op);
                            } else {
                                //The way this is done is by adding this OrderProduct to the
                                //list of deletes and updates so that it's deleted and then
                                //inserted again (stupid, but let's just roll with it)
                                op.setQty(new BigDecimal(newQty));
                                deletes.add(op);
                                updates.add(op);
                            }
                        }
                });
            });
		//Update the cart with all quantities
		for (OrderProduct op : updates) {
			cart.getOrderProducts().add(op);
		}
		//Then delete the order lines don't need
		for (OrderProduct op : deletes) {
			cart.getOrderProducts().remove(op);
		}
		modelAndView.addObject("cartUpdated", true);
	}
}
