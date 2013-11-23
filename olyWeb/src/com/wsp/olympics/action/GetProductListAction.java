package com.wsp.olympics.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.Product;
import com.wsp.olympics.service.CategoryService;
import com.wsp.olympics.service.ProductService;

/**
 * Action class for getting a list of products, with the user including or not including a category to narrow the list down.
 * 
 * @author Patrick Altaie
 */
public class GetProductListAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("cat");
		ProductService productService = new ProductService();
		CategoryService catService = new CategoryService();
		List<Product> products = null;
		
		if (category != null) {
			//If we have a category get all products with that category
			products = productService.getProductsByCategoryName(category);
			request.setAttribute("category", category);
		} else {
			//Otherwise just get all products
			products = productService.getAllProducts();
		}
		request.setAttribute("products", products);
		request.setAttribute("categories", catService.getAllCategories());
		request.getRequestDispatcher("/WEB-INF/jsp/products.jsp").forward(request, response);
	}
}
