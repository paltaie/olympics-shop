package com.wsp.olympics.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminAction {

    @RequestMapping("/admin")
    public ModelAndView doAdmin() {
        return new ModelAndView("admin");
    }
}
