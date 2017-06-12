package com.wsp.supplierapp.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import com.wsp.olympics.ws.Order;
import com.wsp.olympics.ws.types.PaidOrder;
import com.wsp.supplierapp.util.SoapLoggingHandler;
import com.wsp.supplierapp.util.WsseAuthHandler;

public class OrderService {
	
	private Properties props;
	private Order orderPort;
	
	public OrderService(final Properties props) {
		this.props = props;
		com.wsp.olympics.ws.OrderService orderService =
				new com.wsp.olympics.ws.OrderService(getServiceURL());
		orderService.setHandlerResolver(new HandlerResolver() {
			@Override
			public List<Handler> getHandlerChain(PortInfo portInfo) {
				String wsUsername = props.getProperty("ws.username");
				String wsPassword = props.getProperty("ws.password");
				List<Handler> handlers = new ArrayList<Handler>();
				handlers.add(new WsseAuthHandler(wsUsername, wsPassword));
				handlers.add(new SoapLoggingHandler());
				return handlers;
			}
		});
		orderPort = orderService.getOrderPort();
	}
	
	private URL getServiceURL() {
		URL serviceURL = null;
		String serviceString = (String) props.get("svc.url");
		try {
			serviceURL = new URL(serviceString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return serviceURL;
	}
	
	public List<PaidOrder> getPaidOrders() {
		return orderPort.getPaidOrders();
	}
	
	public void updateOrderStatus(String orderId, String status) {
		orderPort.updateOrderStatus(orderId, status);
	}
}
