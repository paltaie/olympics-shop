package com.wsp.olympics.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.wsp.olympics.model.Category;
import com.wsp.olympics.model.Product;

@Remote
public interface ProductBeanRemote {
	/**
	 * Gets all available products
	 * 
	 * @return a list of all products available
	 */
	public List<Product> getAllProducts();
	/**
	 * Gets a list of products given the {@link Category Category} provided
	 * @param category the category to search for
	 * @return a list of products, or an empty list if there are no products in that category
	 */
	public List<Product> getProductsByCategory(Category category);
	/**
	 * Gets a product by its ID. Returns null if the product can't be found.
	 * 
	 * @param productCode the product code to search for
	 * @return a single product, or null if the ID can't be resolved
	 */
	public Product getProductByCode(String productCode);
}
