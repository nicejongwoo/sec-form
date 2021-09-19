package com.sec.form.controller;

import com.sec.form.domain.Item;
import com.sec.form.prop.ShopProperties;
import com.sec.form.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/item")
@Controller
public class ItemController {

    private ItemService itemService;
    private final ShopProperties shopProperties;

    @GetMapping("/register")
    public void registerForm(Model model) {
        model.addAttribute(new Item());
    }

}
