package com.sec.form.controller;

import com.sec.form.service.CodeService;
import com.sec.form.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final CodeService codeService;

}
