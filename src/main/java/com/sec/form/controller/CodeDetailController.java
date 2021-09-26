package com.sec.form.controller;

import com.sec.form.domain.CodeDetail;
import com.sec.form.domain.CodeLabelValue;
import com.sec.form.service.CodeDetailService;
import com.sec.form.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Secured("ROLE_ADMIN")
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
    public String register(CodeDetail codeDetail, RedirectAttributes redirectAttributes) {
        codeDetailService.register(codeDetail);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/codedetail/list";
    }

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", codeDetailService.list());
    }

    @GetMapping("/read")
    public void read(CodeDetail codeDetail, Model model) {
        model.addAttribute(codeDetailService.read(codeDetail));
        model.addAttribute("groupCodeList", codeService.getCodeGroupList());
    }

    @GetMapping("/modify")
    public void modifyForm(CodeDetail codeDetail, Model model) {
        model.addAttribute(codeDetailService.read(codeDetail));
        List<CodeLabelValue> codeGroupList = codeService.getCodeGroupList();
        model.addAttribute("groupCodeList", codeGroupList);
    }

    @PostMapping("/modify")
    public String modify(CodeDetail codeDetail, RedirectAttributes redirectAttributes) {
        codeDetailService.modify(codeDetail);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/codedetail/list";
    }

    @PostMapping("/remove")
    public String remove(CodeDetail codeDetail, RedirectAttributes redirectAttributes) {
        codeDetailService.remove(codeDetail);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/codedetail/list";
    }

}
