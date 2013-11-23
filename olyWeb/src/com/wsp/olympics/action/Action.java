package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A template for an action to be performed by the application based on a user request
 * 
 * @author Patrick Altaie
 */
public interface Action {
	/**
	 * Execute this action. Has access to the request and response objects to have access to the session, dispatcher, etc.
	 * 
	 * @param request the <code>HttpServletRequest</code> we are using, contains session info, cookies, etc.
	 * @param response the <code>HttpServletResponse</code> for the response message
	 * @throws ServletException same behaviour as {@link javax.servlet.http.HttpServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse) service} method
	 * @throws IOException same behaviour as {@link javax.servlet.http.HttpServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse) service} method
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
