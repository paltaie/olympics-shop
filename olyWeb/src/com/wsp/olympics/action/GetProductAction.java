package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.model.Product;
import com.wsp.olympics.service.ProductService;

/**
 * Gets a requested product by ID. If not found, displays an error message.
 * 
 * @author Patrick Altaie
 */
public class GetProductAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService service = new ProductService();
		String productCode = request.getParameter("id");
		Product product = service.getProductByCode(productCode);
		
		if (product != null) {
			//If the product isn't null then attach it to the request
			request.setAttribute("product", product);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/product.jsp").forward(request, response);
	}
}
