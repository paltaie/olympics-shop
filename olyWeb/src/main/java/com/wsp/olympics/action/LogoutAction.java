package com.wsp.olympics.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@Controller
public class LogoutAction {

	@RequestMapping("/logout")
	public ModelAndView execute(HttpServletRequest request)
			throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView("admin/logout");
		HttpSession session = request.getSession();
		//Store the user name as an attribute because we're about to invalidate the session
		//(and by association forget his or her name)
		modelAndView.addObject("userName", request.getRemoteUser());
		session.invalidate();
		return modelAndView;
	}
}
