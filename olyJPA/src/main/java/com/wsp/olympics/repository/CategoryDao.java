package com.wsp.olympics.repository;

import com.wsp.olympics.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Generic interface for a category accessor.
 * 
 * @author WSP
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
	/**
	 * Gets a category given its name.
	 * 
	 * @param name the name of the category we're looking for
	 * @return the category's name, or null if there is no category with that name.
	 */
	Category findByCategoryName(String name);
}
