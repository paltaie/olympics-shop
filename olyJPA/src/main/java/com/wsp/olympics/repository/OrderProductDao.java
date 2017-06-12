package com.wsp.olympics.repository;

import java.util.List;

import com.wsp.olympics.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Performs various operations on OrderProducts (an order/product linking)
 * 
 * @author WSP
 */
@Repository
public interface OrderProductDao extends JpaRepository<OrderProduct, Long> {
	/**
	 * Retrieves an order/product correlation by the master order ID (one order has multiple order-product linkings)
	 * 
	 * @param orderId the order ID to look for
	 * @return the list of order/products to get
	 */
	public List<OrderProduct> findByOrderOrderId(Long orderId);
}
