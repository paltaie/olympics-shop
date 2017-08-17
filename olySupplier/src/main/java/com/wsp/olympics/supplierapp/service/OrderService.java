package com.wsp.olympics.supplierapp.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.ws.types.UpdateOrderStatusRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderService {

	private RestTemplate restTemplate = new RestTemplate();

    private String serviceUrl;
    private String wsUsername;
    private String wsPassword;

    public OrderService(
            @Value("${svc.url}") String serviceUrl,
            @Value("${ws.username}") String wsUsername,
            @Value("${ws.password}") String wsPassword) {
        this.serviceUrl = serviceUrl;
        this.wsUsername = wsUsername;
        this.wsPassword = wsPassword;
    }

	public List<ShoppingCart> getPaidOrders() {
		HttpEntity<UpdateOrderStatusRequest> httpEntity = new HttpEntity<>(createHttpHeaders());
		return Arrays.asList(restTemplate.exchange(serviceUrl + "/paidOrders", HttpMethod.GET, httpEntity, ShoppingCart[].class).getBody());
	}
	
	public void updateOrderStatus(String orderId, String status) {
        UpdateOrderStatusRequest updateOrderStatusRequest =
                new UpdateOrderStatusRequest(orderId, status);
        HttpEntity<UpdateOrderStatusRequest> httpEntity = new HttpEntity<>(updateOrderStatusRequest, createHttpHeaders());
        restTemplate.exchange(serviceUrl + "/updateOrderStatus", HttpMethod.POST, httpEntity, String.class);
	}

	private HttpHeaders createHttpHeaders() {
		String usernamePasswordCombo = String.format("%s:%s", wsUsername, wsPassword);
		String base64ifiedCombo = Base64.getEncoder().encodeToString(usernamePasswordCombo.getBytes());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.AUTHORIZATION, "Basic " + base64ifiedCombo);
		return httpHeaders;
	}
}
