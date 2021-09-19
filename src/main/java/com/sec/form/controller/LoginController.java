package com.sec.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(String error, String logout, Model model) {
        if (error != null) {
            model.addAttribute("error", "Login Error");
        }
        if (logout != null) {
            model.addAttribute("logout", "Logout");
        }
        return "auth/loginForm";
    }

}
