package com.sec.form.controller;

import com.sec.form.service.CodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/codegroup")
@Controller
public class CodeGroupController {

    private final CodeGroupService codeGroupService;
}
