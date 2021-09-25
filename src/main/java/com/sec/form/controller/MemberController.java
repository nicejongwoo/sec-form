package com.sec.form.controller;

import com.sec.form.common.security.domain.CustomUser;
import com.sec.form.domain.CodeLabelValue;
import com.sec.form.domain.Member;
import com.sec.form.service.CodeService;
import com.sec.form.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final CodeService codeService;

    private final PasswordEncoder passwordEncoder;

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

        //패스워드 암호화
        String userPw = member.getUserPw();
        member.setUserPw(passwordEncoder.encode(userPw));

        memberService.register(member);

        redirectAttributes.addFlashAttribute("userName", member.getUserName());
        return "redirect:/user/registerSuccess";
    }

    @GetMapping("/registerSuccess")
    public void registerSuccess(Model model) {

    }
}
