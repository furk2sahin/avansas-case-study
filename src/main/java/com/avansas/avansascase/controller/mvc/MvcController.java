package com.avansas.avansascase.controller.mvc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MvcController {

    @GetMapping("login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("register")
    public String getRegister(){
        return "signup";
    }

    @GetMapping("listUsers")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String listUsers(){
        return "listUsers";
    }
}
