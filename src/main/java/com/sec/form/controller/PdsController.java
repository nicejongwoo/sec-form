package com.sec.form.controller;

import com.sec.form.domain.Pds;
import com.sec.form.prop.ShopProperties;
import com.sec.form.service.PdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/pds")
@Controller
public class PdsController {

    private final PdsService pdsService;
    private final ShopProperties shopProperties;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute(new Pds());
        return "pds/register";
    }

}
