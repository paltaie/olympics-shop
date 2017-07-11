package com.wsp.olympics.action.async;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class CartTableController {

    private ShoppingCartService cartService;

    @Autowired
    public CartTableController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("async/cartTable")
    public ModelAndView getCartTable(HttpSession session,
                                     @RequestParam(value = "order_id", required = false) Long orderId) {
        ModelAndView modelAndView = new ModelAndView("async/cartTable");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (orderId != null) {
            ShoppingCart resultCart = cartService.getCartByOrderId(orderId);
            modelAndView.addObject("cart", resultCart);
        } else {
            modelAndView.addObject("cart", cart);
        }
        return modelAndView;
    }
}
