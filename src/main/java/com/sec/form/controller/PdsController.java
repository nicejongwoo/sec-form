package com.sec.form.controller;

import com.sec.form.common.util.UploadFileUtils;
import com.sec.form.domain.Pds;
import com.sec.form.prop.ShopProperties;
import com.sec.form.service.PdsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pds")
@Controller
public class PdsController {

    private final PdsService pdsService;
    private final ShopProperties shopProperties;

    @ResponseBody
    @PostMapping(value = "/uploadAjax", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws IOException {
        String savedName = UploadFileUtils.uploadFile(shopProperties.getUploadPath(), file.getOriginalFilename(), file.getBytes());
        log.info("savedName= {}", savedName);
        return new ResponseEntity<String>(savedName, HttpStatus.CREATED);
    }

    @ResponseBody
    @GetMapping(value = "/getAttach/{itemId}")
    public List<String> getAttach(@PathVariable("itemId") Integer itemId) {
        List<String> attach = pdsService.getAttach(itemId);
        return attach;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute(new Pds());
        return "pds/register";
    }

    @PostMapping("/register")
    public String register(Pds pds, RedirectAttributes redirectAttributes) {
        pdsService.register(pds);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/pds/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Pds> pdsList = pdsService.list();
        model.addAttribute("list", pdsList);
        return "pds/list";
    }

    @GetMapping("/read")
    public String read(Integer itemId, Model model) {
        Pds pds = pdsService.read(itemId);
        model.addAttribute("pds", pds);
        return "pds/read";
    }

    @GetMapping("/modify")
    public String modifyForm(Integer itemId, Model model) {
        Pds pds = pdsService.read(itemId);
        model.addAttribute("pds", pds);
        return "pds/modify";
    }

    @PostMapping("/modify")
    public String modify(Pds pds, RedirectAttributes redirectAttributes) {
        pdsService.modify(pds);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/pds/list";
    }

    @PostMapping("/remove")
    public String remove(Integer itemId, RedirectAttributes redirectAttributes) {
        pdsService.remove(itemId);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/pds/list";
    }

}
