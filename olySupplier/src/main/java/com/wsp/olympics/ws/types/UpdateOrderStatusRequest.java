package com.wsp.olympics.ws.types;

public class UpdateOrderStatusRequest {
    private String orderNumber;
    private String status;

    public UpdateOrderStatusRequest(String orderNumber, String status) {
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
