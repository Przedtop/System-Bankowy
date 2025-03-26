package com.przedtop.front.bank.system.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.exceptions.TemplateInputException;

@Controller
public class DefaultViewController implements ErrorController {

    private final static Logger logger = LoggerFactory.getLogger(DefaultViewController.class);

    @GetMapping("/error")
    public RedirectView handleError() {
        try {
            return new RedirectView("/");
        } catch (TemplateInputException e) {
            logger.debug("Template not found: errorPage", e);
            return new RedirectView("/");
        }
    }

    @GetMapping("/{page}")
    public String dynamicPage(@PathVariable String page) {
        try {
            return page;
        } catch (TemplateInputException e) {
            logger.debug("Template not found: {}", page, e);
            return "errorPage";
        }
    }

    @GetMapping("/{page}/{page2}")
    public String dynamicPageStage2(@PathVariable String page, @PathVariable String page2) {
        try {
            return page + "/" + page2;
        } catch (TemplateInputException e) {
            logger.debug("Template not found: {}/{}", page, page2, e);
            return "errorPage";
        }
    }
}