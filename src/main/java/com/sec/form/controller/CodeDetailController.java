package com.sec.form.controller;

import com.sec.form.domain.CodeDetail;
import com.sec.form.service.CodeDetailService;
import com.sec.form.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/register")
    public void register(CodeDetail codeDetail, RedirectAttributes redirectAttributes) {
        codeDetailService.register(codeDetail);
        redirectAttributes.addFlashAttribute("msg", "success");
//        return "redirect:/list";
    }

}
