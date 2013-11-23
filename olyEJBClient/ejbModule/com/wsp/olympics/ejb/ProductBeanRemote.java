package com.wsp.olympics.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.wsp.olympics.model.Category;
import com.wsp.olympics.model.Product;

@Remote
public interface ProductBeanRemote {
	public List<Product> getAllProducts();
	public List<Product> getProductsByCategory(Category category);
	public Product getProductByCode(String productCode);
}
