package com.sec.form.controller;

import com.sec.form.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/item")
@Controller
public class ItemController {

    private ItemService itemService;

}
