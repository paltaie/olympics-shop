package com.wsp.olympics.repository;

import java.util.List;

import com.wsp.olympics.model.Category;
import com.wsp.olympics.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Performs various product-related operations
 * 
 * @author WSP
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
	/**
	 * Gets a list of products given the {@link Category Category} provided
	 * @param category the category to search for
	 * @return a list of products, or an empty list if there are no products in that category
	 */
	List<Product> findByCategory(Category category);
}