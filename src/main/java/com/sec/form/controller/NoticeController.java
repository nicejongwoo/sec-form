package com.sec.form.controller;

import com.sec.form.domain.Notice;
import com.sec.form.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/notice")
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/register")
    public void registerForm(Model model) {
        model.addAttribute(new Notice());
    }

    @PostMapping("/register")
    public String register(Notice notice, RedirectAttributes redirectAttributes) {
        noticeService.register(notice);
        redirectAttributes.addFlashAttribute("msg", "success");

        return "redirect:/notice/list";
    }

    @GetMapping("/list")
    public void list(Model model) {
        List<Notice> notices = noticeService.list();
        model.addAttribute("list", notices);
    }

    @GetMapping("/read")
    public void read(int noticeNo, Model model) {
        Notice notice = noticeService.read(noticeNo);
        model.addAttribute("notice", notice);
    }

    @GetMapping("/modify")
    public void modifyForm(int noticeNo, Model model) {
        Notice notice = noticeService.read(noticeNo);
        model.addAttribute("notice", notice);
    }

    @PostMapping("/modify")
    public String modify(Notice notice, RedirectAttributes redirectAttributes) {
        noticeService.modify(notice);
        redirectAttributes.addFlashAttribute("msg", "success");

        return "redirect:/notice/list";
    }

    @PostMapping("/remove")
    public String remove(int noticeNo, RedirectAttributes redirectAttributes) {
        noticeService.remove(noticeNo);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/notice/list";
    }




}
