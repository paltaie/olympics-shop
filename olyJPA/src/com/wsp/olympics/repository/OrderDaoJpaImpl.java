package com.wsp.olympics.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.wsp.olympics.model.Order;

public class OrderDaoJpaImpl extends DaoJpaImpl implements OrderDao {
	public OrderDaoJpaImpl(EntityManager em) {
		super(em);
	}

	@Override
	public String addOrder(Order order) {
		em.persist(order);
		return order.getOrderId();
	}

	@Override
	public Order getOrderById(String orderId) {
		return em.find(Order.class, orderId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersByStatus(String status) {
		return em.createNamedQuery("Order.ordersByStatus")
				.setParameter("status", status)
				.getResultList();
	}

	@Override
	public void updateOrderStatus(String orderId, String status) {
		Order order = getOrderById(orderId);
		order.setStatus(status);
		em.merge(order);
	}
}
