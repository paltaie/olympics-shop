package com.wsp.olympics.ws;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.OrderService;
import com.wsp.olympics.service.ShoppingCartService;
import com.wsp.olympics.ws.types.PaidOrder;
import com.wsp.olympics.ws.types.UpdateOrderStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "supplier/updateOrderStatus", method = RequestMethod.POST)
    public ResponseEntity<String> updateOrderStatus(@RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        String orderNumber = updateOrderStatusRequest.getOrderNumber();
        String status = updateOrderStatusRequest.getStatus();

        com.wsp.olympics.model.Order order = orderService.getOrderByOrderNumber(orderNumber);
        if (order == null) {
            return new ResponseEntity<>("Could not find order with ID " + orderNumber, HttpStatus.NOT_FOUND);
        }
        Long orderId = order.getOrderId();
        if ("SENT".equalsIgnoreCase(status.trim())) {
            orderService.updateOrderStatus(orderId, status);
        } else {
            return new ResponseEntity<>("You can only set the status of an order to \"SENT\"", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "supplier/paidOrders", method = RequestMethod.GET)
    public List<PaidOrder> getPaidOrders() {
        List<PaidOrder> responses = new ArrayList<>();
        List<ShoppingCart> carts = shoppingCartService.getCartsByStatus(new String[] {"PAID"});
        carts.forEach(cart ->
            responses.add(new PaidOrder(cart.getOrder(), cart.getOrderProducts()))
        );
        return responses;
    }
}
