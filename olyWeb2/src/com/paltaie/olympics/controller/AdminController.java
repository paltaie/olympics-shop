package com.paltaie.olympics.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@RequestMapping({"/admin.oly", "/admin/admin.oly"})
	public ModelAndView getAdminFrontend() {
		return new ModelAndView("admin");
	}
	
	@RequestMapping("/logout.oly")
	public ModelAndView logout(HttpServletRequest request, HttpSession session) {
		return new ModelAndView("admin/logout");
	}
}
