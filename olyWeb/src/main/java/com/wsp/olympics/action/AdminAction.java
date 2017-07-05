package com.wsp.olympics.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminAction {

    @RequestMapping("/admin")
    public String doAdmin() {
        return "admin";
    }
}
