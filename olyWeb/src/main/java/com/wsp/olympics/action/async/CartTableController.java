package com.wsp.olympics.action.async;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CartTableController {

    private ShoppingCartService cartService;

    @Autowired
    public CartTableController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping("async/cartTable")
    public ModelAndView getCartTable(HttpServletRequest request,
                                     HttpSession session,
                                     @RequestParam(value = "order_id", required = false) Long orderId) {
        ModelAndView modelAndView = new ModelAndView("async/cartTable");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (request.getParameter("order_id") != null) {
            ShoppingCart resultCart = cartService.getCartByOrderId(orderId);
            modelAndView.addObject("cart", resultCart);
        } else {
            modelAndView.addObject("cart", cart);
        }
        return modelAndView;
    }
}
