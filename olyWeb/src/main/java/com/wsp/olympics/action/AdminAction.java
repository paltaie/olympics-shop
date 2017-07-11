package com.wsp.olympics.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminAction {

    @GetMapping("/admin")
    public String doAdmin() {
        return "admin";
    }
}
