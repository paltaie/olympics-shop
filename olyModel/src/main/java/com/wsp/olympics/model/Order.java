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

/**
 * The persistent class for the ORDERS database table.
 * 
 */
@Entity
@Table(name="ORDERS")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDERS_ORDERID_GENERATOR", sequenceName="ORDERS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_ORDERID_GENERATOR")
	@Column(name="ORDER_ID")
	private Long orderId;

	@Column(name="ORDER_NUMBER")
	private String orderNumber;

	private String status;

	//uni-directional many-to-one association to Customer
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

    public Order() {
    }

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}