package com.sec.form.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class CommonController {

    @GetMapping("/error/accessError")
    public void accessDenied(Authentication authentication, Model model) {
        log.info("accessDenied= {}", authentication);
        model.addAttribute("msg", "access denied");
    }
}
