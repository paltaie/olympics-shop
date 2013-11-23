package com.wsp.olympics.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wsp.olympics.model.Category;
import com.wsp.olympics.repository.CategoryDao;
import com.wsp.olympics.repository.CategoryDaoJpaImpl;

/**
 * Session Bean implementation class CategoryBean
 */
@Stateless(mappedName = "ejb/category")
public class CategoryBean implements CategoryBeanRemote, CategoryBeanLocal {

	@PersistenceContext(unitName="olyJPA")
	private EntityManager em;
	private CategoryDao dao;
	
	@Override
	public List<Category> getAllCategories() {
		return dao.getAllCategories();
	}

	@Override
	public Category getCategoryByName(String name) {
		return dao.getCategoryByName(name);
	}
	
	@PostConstruct
	private void initDao() {
		if (dao == null) {
			dao = new CategoryDaoJpaImpl(em);
		}
	}
}
