package com.wsp.olympics.supplierapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.wsp.olympics.ws.types.PaidOrder;
import com.wsp.olympics.ws.types.UpdateOrderStatusRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.http.HTTPException;

public class OrderService {
	
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	public OrderService(final Properties props) {
		baseUrl = props.getProperty("svc.url");
	}
	
	public List<PaidOrder> getPaidOrders() {
	    return Arrays.asList(restTemplate.getForObject(baseUrl + "/paidOrders", PaidOrder[].class));
	}
	
	public void updateOrderStatus(String orderId, String status) {
        UpdateOrderStatusRequest updateOrderStatusRequest =
                new UpdateOrderStatusRequest(orderId, status);
        HttpEntity<UpdateOrderStatusRequest> httpEntity = new HttpEntity<>(updateOrderStatusRequest);
        restTemplate.exchange(baseUrl + "/updateOrderStatus", HttpMethod.POST, httpEntity, String.class);
	}

}
