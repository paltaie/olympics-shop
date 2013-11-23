package com.wsp.olympics.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wsp.olympics.model.Category;
import com.wsp.olympics.model.Product;
import com.wsp.olympics.repository.ProductDao;
import com.wsp.olympics.repository.ProductDaoJpaImpl;

/**
 * Session Bean implementation class ProductBean
 */
@Stateless(mappedName = "ejb/product")
public class ProductBean implements com.wsp.olympics.ejb.ProductBeanRemote, ProductBeanLocal {

	@PersistenceContext(unitName="olyJPA")
	private EntityManager em;
	private ProductDao dao;
	
	@Override
	public List<Product> getAllProducts() {
		return dao.getAllProducts();
	}

	@Override
	public List<Product> getProductsByCategory(Category category) {
		return dao.getProductsByCategory(category);
	}

	@Override
	public Product getProductByCode(String productCode) {
		return dao.getProductByCode(productCode);
	}
	
	@PostConstruct
	private void initDao() {
		if (dao == null) {
			dao = new ProductDaoJpaImpl(em);
		}
	}
}
