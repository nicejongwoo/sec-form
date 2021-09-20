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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute(new Pds());
        return "pds/register";
    }

    @PostMapping("/register")
    public String register(Pds pds, RedirectAttributes redirectAttributes) {
        System.out.println("file=" + pds.getFiles());
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
}
