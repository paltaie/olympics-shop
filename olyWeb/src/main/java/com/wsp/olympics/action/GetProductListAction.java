package com.wsp.olympics.action;

import com.wsp.olympics.model.Product;
import com.wsp.olympics.service.CategoryService;
import com.wsp.olympics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Action class for getting a list of products, with the user including or not including a category to narrow the list down.
 * 
 * @author Patrick Altaie
 */
@Controller
public class GetProductListAction {

	private ProductService productService;
	private CategoryService catService;

	@Autowired
	public GetProductListAction(ProductService productService, CategoryService catService) {
		this.productService = productService;
		this.catService = catService;
	}

	@RequestMapping("/products")
	public ModelAndView execute(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("products");
		String category = request.getParameter("cat");
		List<Product> products = null;

		if (category != null) {
			//If we have a category get all products with that category
			products = productService.getProductsByCategoryName(category);
			modelAndView.addObject("category", category);
		} else {
			//Otherwise just get all products
			products = productService.getAllProducts();
		}
		modelAndView.addObject("products", products);
		modelAndView.addObject("categories", catService.getAllCategories());
		return modelAndView;
	}
}
