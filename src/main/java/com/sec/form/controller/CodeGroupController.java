package com.sec.form.controller;

import com.sec.form.domain.CodeGroup;
import com.sec.form.service.CodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/codegroup")
@Controller
public class CodeGroupController {

    private final CodeGroupService codeGroupService;

    //등록폼
    @GetMapping("/register")
    public void registerForm(Model model) {
        model.addAttribute("codeGroup", new CodeGroup());
    }
}
