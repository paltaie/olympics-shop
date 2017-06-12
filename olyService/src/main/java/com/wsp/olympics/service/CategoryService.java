package com.wsp.olympics.service;

import java.util.List;

import com.wsp.olympics.model.Category;
import com.wsp.olympics.repository.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryService {
	private CategoryDao categoryDao;

	@Autowired
	public CategoryService(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public List<Category> getAllCategories() {
		return categoryDao.findAll();
	}
}
