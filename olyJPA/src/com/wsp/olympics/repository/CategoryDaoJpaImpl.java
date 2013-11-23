package com.wsp.olympics.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.wsp.olympics.model.Category;

public class CategoryDaoJpaImpl extends DaoJpaImpl implements CategoryDao {
	public CategoryDaoJpaImpl(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		return em.createNamedQuery("Category.allCategories")
				.getResultList();
	}

	@Override
	public Category getCategoryByName(String name) {
		return (Category) em.createNamedQuery("Category.categoryByName")
				.setParameter("categoryName", name)
				.getSingleResult();
	}
}
