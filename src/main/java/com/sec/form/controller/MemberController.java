package com.sec.form.controller;

import com.sec.form.domain.CodeLabelValue;
import com.sec.form.domain.Member;
import com.sec.form.service.CodeService;
import com.sec.form.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final CodeService codeService;

    @GetMapping("/register")
    public void registerForm(Member member, Model model) {
        //TODO 작업코드 목록을 조회 동적으로 처리
        String groupCode = "A01";
        List<CodeLabelValue> codeGroupList = codeService.getCodeList(groupCode);
        model.addAttribute("jobList", codeGroupList);
    }

    @PostMapping("/register")
    public String register(@Validated Member member, BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            //TODO 작업코드 목록을 조회 동적으로 처리
            String groupCode = "A01";
            List<CodeLabelValue> codeGroupList = codeService.getCodeList(groupCode);
            model.addAttribute("jobList", codeGroupList);
            return "user/register";
        }
        memberService.register(member);
        redirectAttributes.addFlashAttribute("userName", member.getUserName());
        return "redirect:/user/registerSuccess";
    }

    @GetMapping("/registerSuccess")
    public void registerSuccess(Model model) {

    }
}
