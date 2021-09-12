package com.sec.form.controller;

import com.sec.form.service.CodeDetailService;
import com.sec.form.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/codedetail")
public class CodeDetailController {

    private final CodeDetailService codeDetailService;
    private final CodeService codeService;

}
