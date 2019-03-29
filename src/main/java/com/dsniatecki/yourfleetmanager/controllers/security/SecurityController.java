package com.dsniatecki.yourfleetmanager.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String showMyLoginPage(){
        return "security/login";
    }
}
