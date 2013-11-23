package com.wsp.olympics.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.wsp.olympics.model.Category;

@Remote
public interface CategoryBeanRemote {
	/**
	 * Get a list of all available categories
	 * 
	 * @return a list of {@link com.wsp.olympics.model.Category Category}s
	 */
	public List<Category> getAllCategories();
	/**
	 * Gets a category given its name.
	 * 
	 * @param name the name of the category we're looking for
	 * @return the category's name, or null if there is no category with that name.
	 */
	public Category getCategoryByName(String name);
}
