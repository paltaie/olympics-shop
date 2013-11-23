package com.wsp.olympics.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logs the admin user out of the session. This is an admin-only action.
 * 
 * @author Patrick Altaie
 */
public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//Store the user name as an attribute because we're about to invalidate the session
		//(and by association forget his or her name)
		request.setAttribute("userName", request.getRemoteUser());
		session.invalidate();
		request.getRequestDispatcher("/WEB-INF/jsp/admin/logout.jsp").forward(request, response);
	}

}
