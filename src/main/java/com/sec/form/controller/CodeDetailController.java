package com.sec.form.controller;

import com.sec.form.domain.CodeDetail;
import com.sec.form.service.CodeDetailService;
import com.sec.form.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/codedetail")
public class CodeDetailController {

    private final CodeDetailService codeDetailService;
    private final CodeService codeService;

    @GetMapping("/register")
    public void registerForm(Model model) {
        model.addAttribute(new CodeDetail());
        model.addAttribute("groupCodeList", codeService.getCodeGroupList());
    }

}
