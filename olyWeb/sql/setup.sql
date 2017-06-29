-- 
-- AIP Assignment 2 Table DDL
-- ----------------------------
--
-- Drop existing
-- 
DROP TABLE order_product CASCADE CONSTRAINTS PURGE;
DROP TABLE orders CASCADE CONSTRAINTS PURGE;
DROP TABLE customer CASCADE CONSTRAINTS PURGE;
DROP TABLE product CASCADE CONSTRAINTS PURGE;
DROP TABLE category CASCADE CONSTRAINTS PURGE;
--
DROP SEQUENCE customer_seq;
DROP SEQUENCE orders_seq;
DROP SEQUENCE order_product_seq;
-- ----------------------------
--
-- Category Table
--
CREATE TABLE category
(
	category_id NUMBER NOT NULL,
	category_name VARCHAR2(255),
	CONSTRAINT category_id_pk PRIMARY KEY (category_id)
);
--
CREATE BITMAP INDEX category_name ON category(category_name);
-- ----------------------------
--
-- Product Table
--
CREATE TABLE product
(
	product_code NUMBER NOT NULL,
	category_id NUMBER,
	title VARCHAR2(255),
	description VARCHAR2(1024),
	price NUMBER,
	img_path VARCHAR2(255),
	CONSTRAINT product_code_pk PRIMARY KEY (product_code),
	CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES CATEGORY (category_id)
);
-- ----------------------------
--
-- Customer Table
--
CREATE TABLE customer
(
	customer_id VARCHAR2(6) NOT NULL,
	surname VARCHAR2(255),
	given_name VARCHAR2(255),
	email VARCHAR2(255),
	house_no VARCHAR2(255),
	street VARCHAR2(255),
	state VARCHAR2(128),
	suburb VARCHAR2(255),
	postcode VARCHAR2(64),
	country VARCHAR2(64),
	cc_number VARCHAR2(16),
	CONSTRAINT customer_id_pk PRIMARY KEY (customer_id)
);
-- ----------------------------
--
-- Order Table
--
CREATE TABLE orders
(
	order_id VARCHAR2(255) NOT NULL,
	customer_id VARCHAR2(6) NOT NULL,
  	status VARCHAR2(7) NOT NULL CHECK(status IN ('ORDERED', 'PAID', 'SENT')),
  	order_number VARCHAR2(255),
	CONSTRAINT order_id_pk PRIMARY KEY (order_id),
	CONSTRAINT customer_fk FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
-- ----------------------------
--
-- Order Product Relationship Table
--
CREATE TABLE order_product
(
	order_product_id VARCHAR2(255) NOT NULL,
	order_id VARCHAR2(255) NOT NULL,
	product_code NUMBER NOT NULL,
	qty NUMBER NOT NULL,
	CONSTRAINT order_product_fk1 FOREIGN KEY (order_id) REFERENCES orders (order_id),
	CONSTRAINT order_product_fk2 FOREIGN KEY (product_code) REFERENCES product (product_code)
);
-- ----------------------------
--
-- Sequences
--
CREATE SEQUENCE customer_seq;
--
CREATE SEQUENCE orders_seq;
--
CREATE SEQUENCE order_product_seq;
