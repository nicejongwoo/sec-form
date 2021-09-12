package com.sec.form.controller;

import com.sec.form.domain.CodeGroup;
import com.sec.form.service.CodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/register")
    public String register(CodeGroup codeGroup, RedirectAttributes redirectAttributes) {
        codeGroupService.register(codeGroup);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/codegroup/list";
    }

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", codeGroupService.list());
    }

    @GetMapping("/read")
    public void read(String groupCode, Model model) {
        model.addAttribute(codeGroupService.read(groupCode));
    }

    @GetMapping("/modify")
    public void modifyForm(String groupCode, Model model) {
        model.addAttribute("codeGroup", codeGroupService.read(groupCode));
    }

    @PostMapping("/modify")
    public String modify(CodeGroup codeGroup, RedirectAttributes redirectAttributes) {
        codeGroupService.modify(codeGroup);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/codegroup/list";
    }
}
