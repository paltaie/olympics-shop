package com.wsp.olympics.action.async;

import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class CartSizeController {

    @RequestMapping("async/cartSize")
    public ModelAndView getCartSize(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("async/cartSize");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            modelAndView.addObject("cartSize", "0");
        } else {
            int cartSize = 0;
            for (OrderProduct o : cart.getOrderProducts()){
                cartSize += o.getQty().intValue();
            }
            modelAndView.addObject("cartSize", cartSize);
        }
        return modelAndView;
    }
}
