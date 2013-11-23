package com.wsp.olympics.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsp.olympics.action.Action;
import com.wsp.olympics.action.AddToCartAction;
import com.wsp.olympics.action.CheckoutAction;
import com.wsp.olympics.action.ClearCartAction;
import com.wsp.olympics.action.GetOutstandingOrdersAction;
import com.wsp.olympics.action.GetProductAction;
import com.wsp.olympics.action.GetProductListAction;
import com.wsp.olympics.action.LogoutAction;
import com.wsp.olympics.action.TrackOrderAction;
import com.wsp.olympics.action.UpdateCartAction;
import com.wsp.olympics.action.UpdateOrderAction;
import com.wsp.olympics.action.ViewCartAction;

/**
 * Servlet implementation class OlympicsController
 */
public class OlympicsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OlympicsController() {
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
		String action = request.getParameter("action");
		Action forwardAction = null;
		if (action == null || action.isEmpty()) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else if (action.equals("admin") && isAdminRequest(request)) {
			request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
		} else if (action.equals("products")) {
			forwardAction = new GetProductListAction();
		} else if (action.equals("product")) {
			forwardAction = new GetProductAction();
		} else if (action.equals("checkout")) {
			forwardAction = new CheckoutAction();
		} else if (action.equals("logout")) {
			forwardAction = new LogoutAction(); 
		} else if (action.equals("track")) {
			forwardAction = new TrackOrderAction();
		} else if (action.equals("cart")) {
			forwardAction = new ViewCartAction();
		} else if (action.equals("addToCart")) {
			forwardAction = new AddToCartAction();
		} else if (action.equals("updateCart")) {
			forwardAction = new UpdateCartAction();
		} else if (action.equals("clearCart")) {
			forwardAction = new ClearCartAction();
		} else if (action.equals("getOutstanding") && isAdminRequest(request)) {
			forwardAction = new GetOutstandingOrdersAction();
		} else if (action.equals("updateOrder") && isAdminRequest(request)) {
			forwardAction = new UpdateOrderAction();
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		//Only execute an action if we have picked one
		if (forwardAction != null) {
			forwardAction.execute(request, response);
		}
	}
	
	private boolean isAdminRequest(HttpServletRequest request) {
		if (request.getRequestURL().toString().contains("/admin")) {
			return true;
		}
		return false;
	}
}
