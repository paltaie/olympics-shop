package com.wsp.olympics.action.async;

import com.wsp.olympics.model.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class CartSizeController {

    @RequestMapping("async/cartSize")
    @ResponseBody
    public CartSizeResponse getCartSize(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            return new CartSizeResponse(0);
        } else {
            return new CartSizeResponse(cart.getOrderProducts().stream().mapToInt(op -> op.getQty().intValue()).sum());
        }
    }

    private class CartSizeResponse {
        private final int cartSize;

        CartSizeResponse(int cartSize) {
            this.cartSize = cartSize;
        }

        public int getCartSize() {
            return cartSize;
        }
    }
}
