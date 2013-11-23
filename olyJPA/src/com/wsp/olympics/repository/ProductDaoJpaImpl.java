package com.wsp.olympics.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.wsp.olympics.model.Category;
import com.wsp.olympics.model.Product;

public class ProductDaoJpaImpl extends DaoJpaImpl implements ProductDao {
	public ProductDaoJpaImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Product getProductByCode(String productCode) {
		return em.find(Product.class, Long.parseLong(productCode));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByCategory(Category category) {
		return em.createNamedQuery("Product.productsByCategory")
			.setParameter("categoryId", category.getCategoryId())
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		return em.createNamedQuery("Product.allProducts")
				.getResultList();
	}
}