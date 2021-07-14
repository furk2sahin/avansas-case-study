package com.avansas.avansascase.controller.mvc;

import com.avansas.avansascase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MvcController {

    private final UserService userService;

    @Autowired
    public MvcController(UserService userService) {
        this.userService = userService;
    }

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
    public ModelAndView listUsers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.setViewName("listUsers");
        return modelAndView;
    }
}
