package com.wsp.olympics.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wsp.olympics.model.Order;
import com.wsp.olympics.repository.OrderDao;
import com.wsp.olympics.repository.OrderDaoJpaImpl;

/**
 * Session Bean implementation class OrderBean
 */
@Stateless(mappedName="ejb/order")
public class OrderBean implements OrderBeanRemote, OrderBeanLocal {

	@PersistenceContext(unitName="olyJPA")
	private EntityManager em;
	private OrderDao dao;
	
	@Override
	public String addOrder(Order order) {
		return dao.addOrder(order);
	}

	@Override
	public Order getOrderById(String orderId) {
		return dao.getOrderById(orderId);
	}

	@Override
	public List<Order> getOrdersByStatus(String status) {
		return dao.getOrdersByStatus(status);
	}

	@Override
	public void updateOrderStatus(String orderId, String status) {
		dao.updateOrderStatus(orderId, status);
	}
	
	@PostConstruct
	private void initDao() {
		if (dao == null) {
			dao = new OrderDaoJpaImpl(em);
		}
	}
}
