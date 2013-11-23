package com.wsp.olympics.service;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.wsp.olympics.ejb.CategoryBeanRemote;
import com.wsp.olympics.model.Category;

public class CategoryService {
	private CategoryBeanRemote categoryBean;

	public CategoryService() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			categoryBean = (CategoryBeanRemote) ctx.lookup("ejb/category#com.wsp.olympics.ejb.CategoryBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
	
	public List<Category> getAllCategories() {
		return categoryBean.getAllCategories();
	}
}
