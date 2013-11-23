package com.wsp.olympics.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.wsp.olympics.model.Order;

public class OrderDaoJpaImpl extends DaoJpaImpl implements OrderDao {
	public OrderDaoJpaImpl(EntityManager em) {
		super(em);
	}

	@Override
	public String addOrder(Order order) {
		em.persist(order);
		em.flush();
		em.refresh(order);
		return order.getOrderId();
	}

	@Override
	public Order getOrderById(String orderId) {
		return em.find(Order.class, orderId);
	}

	@Override
	public Order getOrderByOrderNumber(String orderNumber) {
		try {
		return (Order) em.createNamedQuery("Order.orderByOrderNumber")
			.setParameter("orderNumber", orderNumber)
			.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("Couldn't find order with order number \"" + orderNumber + "\". Returning null...");
			return null;
		}
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
