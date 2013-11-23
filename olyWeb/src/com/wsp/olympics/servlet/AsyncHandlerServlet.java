package com.wsp.olympics.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wsp.olympics.model.OrderProduct;
import com.wsp.olympics.model.ShoppingCart;
import com.wsp.olympics.service.ShoppingCartService;

/**
 * Servlet implementation class AsyncHandlerServlet
 */
public class AsyncHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncHandlerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		String type = request.getParameter("type");
		String orderId = request.getParameter("order_id");
		if (type.equals("cartTable")) {
			if (request.getParameter("order_id") != null) {
				ShoppingCartService cartService = new ShoppingCartService();
				ShoppingCart resultCart = cartService.getCartByOrderId(orderId);
				request.setAttribute("cart", resultCart);
			} else {
				request.setAttribute("cart", cart);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/async/cartTable.jsp").forward(request, response);
		} else if (type.equals("cartSize")) {
			if (cart == null) {
				request.setAttribute("cartSize", "0");
			} else {
				int cartSize = 0;
				for (OrderProduct o : cart.getOrderProducts()){
					cartSize += o.getQty().intValue();
				}
				request.setAttribute("cartSize", cartSize);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/async/cartSize.jsp").forward(request, response);
		} else if (type.equals("countryList")) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			request.getRequestDispatcher("/WEB-INF/jsp/async/countryList.json").forward(request, response);
		}
	}
}
