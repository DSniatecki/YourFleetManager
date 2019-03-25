package com.dsniatecki.yourfleetmanager.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;

@Controller
class MyErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String showNotFound(Model model){
        model.addAttribute("exceptionMessage", "Page you requested does not exist");
        return "error/404error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
