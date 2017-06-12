package com.wsp.olympics.service;

import com.wsp.olympics.model.Category;
import com.wsp.olympics.model.Product;
import com.wsp.olympics.repository.CategoryDao;
import com.wsp.olympics.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The service interface for various product-related services
 * 
 * @author Patrick Altaie
 */
@Component
public class ProductService {

	private ProductDao productDao;
	private CategoryDao categoryDao;

	@Autowired
	public ProductService(ProductDao productDao, CategoryDao categoryDao) {
		this.productDao = productDao;
		this.categoryDao = categoryDao;
	}

	/**
	 * Gets a list of products with the given category name.
	 * 
	 * @param categoryName the category whose products are to be retrieved
	 * @return a list of products under that category (can be an empty list if no products were found for that category)
	 */
	public List<Product> getProductsByCategoryName(String categoryName) {
		Category category = categoryDao.findByCategoryName(categoryName);
		return productDao.findByCategory(category);
	}

	/**
	 * Gets a list of all products.
	 * 
	 * @return a list of all products
	 */
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

	/**
	 * Gets a product by its code.
	 * 
	 * @param productCode the code to search for
	 * @return the product, or <code>null</code> if the product cannot be found
	 */
	public Product getProductByCode(Long productCode) {
		return productDao.findOne(productCode);
	}
}
