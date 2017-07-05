package com.wsp.olympics.action;

import javax.servlet.http.HttpServletRequest;

import com.wsp.olympics.model.Product;
import com.wsp.olympics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Gets a requested product by ID. If not found, displays an error message.
 * 
 * @author Patrick Altaie
 */
@Controller
public class GetProductAction {
	private ProductService productService;

	@Autowired
	public GetProductAction(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/product")
	public ModelAndView execute(HttpServletRequest request, @RequestParam("id") String productCode) {
		ModelAndView modelAndView = new ModelAndView("product");
		Product product = productService.getProductByCode(Long.valueOf(productCode));
		
		if (product != null) {
			//If the product isn't null then attach it to the request
			modelAndView.addObject("product", product);
		}
		return modelAndView;
	}
}
