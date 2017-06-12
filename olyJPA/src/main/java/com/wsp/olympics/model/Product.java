package com.wsp.olympics.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@NamedQueries({
	@NamedQuery(name="Product.allProducts", query="SELECT p FROM Product p ORDER BY p.title"),
	@NamedQuery(name="Product.productsByCategory", query="SELECT p FROM Product p WHERE p.category.categoryId = :categoryId ORDER BY p.title")
})

/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCT_PRODUCTCODE_GENERATOR", sequenceName="PRODUCT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_PRODUCTCODE_GENERATOR")
	@Column(name="PRODUCT_CODE")
	private long productCode;

	private String description;

	@Column(name="IMG_PATH")
	private String imgPath;

	private BigDecimal price;

	private String title;

	//uni-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	private Category category;

	public Product() {
	}

	public long getProductCode() {
		return this.productCode;
	}

	public void setProductCode(long productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}