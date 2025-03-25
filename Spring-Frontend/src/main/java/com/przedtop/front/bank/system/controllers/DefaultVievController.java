package com.przedtop.front.bank.system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultVievController {
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }
}
