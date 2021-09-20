package com.sec.form.controller;

import com.sec.form.prop.ShopProperties;
import com.sec.form.service.PdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/pds")
@Controller
public class PdsController {

    private final PdsService pdsService;
    private final ShopProperties shopProperties;

}
