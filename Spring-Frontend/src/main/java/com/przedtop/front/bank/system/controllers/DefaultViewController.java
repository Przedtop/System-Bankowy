package com.przedtop.front.bank.system.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultViewController implements ErrorController {

    @GetMapping("/error")
    public RedirectView handleError() {
        return new RedirectView("/adminDashboard");
    }
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }

    @GetMapping("/account/createAccount")
    public String createAccount() {
        return "/account/createAccount";
    }
}
