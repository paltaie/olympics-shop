package com.wsp.olympics.service;

import java.util.List;
import java.util.logging.Level;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.wsp.olympics.ejb.CategoryBeanRemote;
import com.wsp.olympics.ejb.ProductBeanRemote;
import com.wsp.olympics.model.Category;
import com.wsp.olympics.model.Product;
import com.wsp.olympics.utils.LoggingUtils;

/**
 * The service interface for various product-related services
 * 
 * @author Patrick Altaie
 */
public class ProductService {

	private ProductBeanRemote productBean;
	private CategoryBeanRemote categoryBean;

	public ProductService() {
		try {
			InitialContext ctx = new InitialContext();
			productBean = (ProductBeanRemote) ctx.lookup("ejb/product#com.wsp.olympics.ejb.ProductBeanRemote");
			categoryBean = (CategoryBeanRemote) ctx.lookup("ejb/category#com.wsp.olympics.ejb.CategoryBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a list of products with the given category name.
	 * 
	 * @param categoryName the category whose products are to be retrieved
	 * @return a list of products under that category (can be an empty list if no products were found for that category)
	 */
	public List<Product> getProductsByCategoryName(String categoryName) {
		Category category = categoryBean.getCategoryByName(categoryName);
		List<Product> products = productBean.getProductsByCategory(category);
		if (products.size() == 0) {
			LoggingUtils.log(Level.WARNING, "[ProductService]Couldn't find product with category name " + categoryName + ". Returning empty product list...");
		}
		return products;
	}

	/**
	 * Gets a list of all products.
	 * 
	 * @return a list of all products
	 */
	public List<Product> getAllProducts() {
		return productBean.getAllProducts();
	}

	/**
	 * Gets a product by its code.
	 * 
	 * @param productCode the code to search for
	 * @return the product, or <code>null</code> if the product cannot be found
	 */
	public Product getProductByCode(String productCode) {
		return productBean.getProductByCode(productCode);
	}
}
