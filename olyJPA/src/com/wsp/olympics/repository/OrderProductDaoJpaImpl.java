package com.wsp.olympics.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.wsp.olympics.model.OrderProduct;

public class OrderProductDaoJpaImpl extends DaoJpaImpl implements OrderProductDao {
	public OrderProductDaoJpaImpl(EntityManager em) {
		super(em);
	}

	@Override
	public void addOrderProduct(OrderProduct op) {
		em.persist(op);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderProduct> getOrderProductsByOrderId(String orderId) {
		return em.createNamedQuery("OrderProduct.orderProductsByOrderId")
			.setParameter("orderId", orderId)
			.getResultList();
	}
}
