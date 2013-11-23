package com.wsp.olympics.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUSTOMER_CUSTOMERID_GENERATOR", sequenceName="CUSTOMER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_CUSTOMERID_GENERATOR")
	@Column(name="CUSTOMER_ID")
	private String customerId;

	@Column(name="CC_NUMBER")
	private String ccNumber;

	private String country;

	private String email;

	@Column(name="GIVEN_NAME")
	private String givenName;

	@Column(name="HOUSE_NO")
	private String houseNo;

	private String postcode;

	@Column(name="\"STATE\"")
	private String state;

	private String street;

	private String suburb;

	private String surname;

	public Customer() {
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCcNumber() {
		return this.ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getHouseNo() {
		return this.houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuburb() {
		return this.suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}