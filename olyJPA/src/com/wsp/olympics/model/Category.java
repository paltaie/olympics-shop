package com.wsp.olympics.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@NamedQueries({
	@NamedQuery(name="Category.allCategories", query="SELECT c FROM Category c ORDER BY c.categoryName"),
	@NamedQuery(name="Category.categoryByName", query="SELECT c FROM Category c WHERE UPPER(c.categoryName) = UPPER(:categoryName) ORDER BY c.categoryName"),
})

/**
 * The persistent class for the CATEGORY database table.
 * 
 */
@Entity
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORY_CATEGORYID_GENERATOR", sequenceName="CATEGORY_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORY_CATEGORYID_GENERATOR")
	@Column(name="CATEGORY_ID")
	private long categoryId;

	@Column(name="CATEGORY_NAME")
	private String categoryName;

	public Category() {
	}

	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}