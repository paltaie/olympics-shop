package com.paltaie.olympics.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paltaie.olympics.service.CategoryService;
import com.paltaie.olympics.service.ProductService;
import com.wsp.olympics.model.Category;
import com.wsp.olympics.model.Product;

@Controller
public class ProductController {
	
	private ProductService productService;
	private CategoryService categoryService;
	
	public ProductController() {
		productService = new ProductService();
		categoryService = new CategoryService();
	}
	
	@RequestMapping("/products.oly")
	public ModelAndView listProducts(@RequestParam(value="cat", required=false) String categoryName) {
		ModelAndView mav = new ModelAndView("products");
		List<Product> products = null;
		if (categoryName == null || categoryName.isEmpty()) {
			products = productService.getAllProducts();
		} else {
			products = productService.getProductsByCategoryName(categoryName);
			mav.addObject("category", categoryName);
		}
		List<Category> categories = categoryService.getAllCategories();
		mav.addObject("products", products);
		mav.addObject("categories", categories);
		return mav;
	}
	
	@RequestMapping("/product.oly")
	public ModelAndView getProduct(@RequestParam("id") String productCode) {
		ModelAndView mav = new ModelAndView("product");
		Product product = productService.getProductByCode(productCode);
		mav.addObject("product", product);
		return mav;
	}
}
