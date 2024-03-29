package com.wsp.olympics.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The persistent class for the ORDER_PRODUCT database table.
 * 
 */
@Entity
@Table(name="ORDER_PRODUCT")
public class OrderProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDER_PRODUCT_ORDERPRODUCTID_GENERATOR", sequenceName="ORDER_PRODUCT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_PRODUCT_ORDERPRODUCTID_GENERATOR")
	@Column(name="ORDER_PRODUCT_ID")
	private Long orderProductId;

	private BigDecimal qty;

	//uni-directional many-to-one association to Order
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="ORDER_ID")
	private Order order;

	//uni-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="PRODUCT_CODE")
	private Product product;

	public OrderProduct() {
	}

	public Long getOrderProductId() {
		return this.orderProductId;
	}

	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}