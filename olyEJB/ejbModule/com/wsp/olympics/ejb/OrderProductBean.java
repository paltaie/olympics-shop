package com.wsp.olympics.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.repository.OrderProductDao;
import com.wsp.olympics.repository.OrderProductDaoJpaImpl;

/**
 * Session Bean implementation class OrderProductBean
 */
@Stateless(mappedName = "ejb/orderProduct")
public class OrderProductBean implements OrderProductBeanRemote, OrderProductBeanLocal {

	@PersistenceContext(unitName="olyJPA")
	private EntityManager em;
	private OrderProductDao dao;
	
	@Override
	public void addOrderProduct(OrderProduct op) {
		dao.addOrderProduct(op);
	}

	@Override
	public List<OrderProduct> getOrderProductsByOrderId(String orderId) {
		return dao.getOrderProductsByOrderId(orderId);
	}
	
	@PostConstruct
	private void initDao() {
		if (dao == null) {
			dao = new OrderProductDaoJpaImpl(em);
		}
	}
}
