package com.wsp.olympics.supplierapp.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.ws.types.UpdateOrderStatusRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class OrderService {

	private final Properties props;
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	public OrderService(final Properties props) {
		this.props = props;
		baseUrl = props.getProperty("svc.url");
	}
	
	public List<ShoppingCart> getPaidOrders() {
		HttpEntity<UpdateOrderStatusRequest> httpEntity = new HttpEntity<>(createHttpHeaders());
		return Arrays.asList(restTemplate.exchange(baseUrl + "/paidOrders", HttpMethod.GET, httpEntity, ShoppingCart[].class).getBody());
	}
	
	public void updateOrderStatus(String orderId, String status) {
        UpdateOrderStatusRequest updateOrderStatusRequest =
                new UpdateOrderStatusRequest(orderId, status);
        HttpEntity<UpdateOrderStatusRequest> httpEntity = new HttpEntity<>(updateOrderStatusRequest, createHttpHeaders());
        restTemplate.exchange(baseUrl + "/updateOrderStatus", HttpMethod.POST, httpEntity, String.class);
	}

	private HttpHeaders createHttpHeaders() {
		String usernamePasswordCombo = String.format("%s:%s", props.getProperty("ws.username"), props.getProperty("ws.password"));
		String base64ifiedCombo = Base64.getEncoder().encodeToString(usernamePasswordCombo.getBytes());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.AUTHORIZATION, "Basic " + base64ifiedCombo);
		return httpHeaders;
	}
}
