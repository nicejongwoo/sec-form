package com.sec.form.controller;

import com.sec.form.domain.Item;
import com.sec.form.prop.ShopProperties;
import com.sec.form.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/item")
@Controller
public class ItemController {

    private final ItemService itemService;
    private final ShopProperties shopProperties;

    @GetMapping("/register")
    public void registerForm(Model model) {
        model.addAttribute(new Item());
    }

    @PostMapping("/register")
    public String register(Item item, RedirectAttributes redirectAttributes) throws IOException {
        if (item.getPicture() != null && item.getPicture().getSize() > 0) {
            MultipartFile pictureFile = item.getPicture();
            String createdPictureFilename = uploadFile(pictureFile.getOriginalFilename(), pictureFile.getBytes());
            item.setPictureUrl(createdPictureFilename);
        }
        if (item.getPreview() != null && item.getPreview().getSize() > 0) {
            MultipartFile previewFile = item.getPreview();
            String createdPreviewFilename = uploadFile(previewFile.getOriginalFilename(), previewFile.getBytes());
            item.setPreviewUrl(createdPreviewFilename);
        }

        itemService.register(item);

        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/item/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Item> items =  itemService.list();
        model.addAttribute("itemList", items);
        return "item/list";
    }

    @GetMapping("/read")
    public String read(Integer itemId, Model model) {
        Item item = itemService.read(itemId);
        model.addAttribute(item);
        return "item/read";
    }

    @GetMapping("/modify")
    public String modifyForm(Integer itemId, Model model) {
        Item item = itemService.read(itemId);
        model.addAttribute(item);
        return "item/modify";
    }

    @PostMapping("/modify")
    public String modify(Item item, RedirectAttributes redirectAttributes) throws IOException {
        MultipartFile picture = item.getPicture();
        if (picture != null && picture.getSize() > 0) {
            String createdFilename = uploadFile(picture.getOriginalFilename(), picture.getBytes());
            item.setPictureUrl(createdFilename);
        }
        MultipartFile preview = item.getPreview();
        if (preview != null && preview.getSize() > 0) {
            String createdFilename = uploadFile(preview.getOriginalFilename(), preview.getBytes());
            item.setPreviewUrl(createdFilename);
        }

        itemService.modify(item);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/item/list";
    }

    @PostMapping("/remove")
    public String remove(Item item, RedirectAttributes redirectAttributes) {
        itemService.remove(item);
        redirectAttributes.addFlashAttribute("msg", "success");
        return "redirect:/item/list";
    }

    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> displayPreview(Integer itemId) throws IOException {
        InputStream inputStream = null;
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();

        String fileName = itemService.getPreview(itemId);
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadPath = shopProperties.getUploadPath();

        try {
            MediaType mediaType = getMediaType(extension);
            inputStream = new FileInputStream(uploadPath + File.separator + fileName);
            if (mediaType != null) {
                headers.setContentType(mediaType);
            }
            entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            inputStream.close();
        }

        return entity;
    }

    @GetMapping("/picture")
    @ResponseBody
    public ResponseEntity<byte[]> displayPicture(Integer itemId) throws IOException {
        InputStream inputStream = null;
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();

        String fileName = itemService.getPicture(itemId);
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadPath = shopProperties.getUploadPath();

        try {
            MediaType mediaType = getMediaType(extension);
            inputStream = new FileInputStream(uploadPath + File.separator + fileName);
            if (mediaType != null) {
                headers.setContentType(mediaType);
            }
            entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            inputStream.close();
        }

        return entity;
    }

    private MediaType getMediaType(String extension) {
        if (extension != null) {
            if (extension.equals("JPG") || extension.equals("jpg") || extension.equals("JPEG") || extension.equals("jpeg")) {
                return MediaType.IMAGE_JPEG;
            }
            if (extension.equals("GIF") || extension.equals("gif")) {
                return MediaType.IMAGE_GIF;
            }
            if (extension.equals("PNG") || extension.equals("png")) {
                return MediaType.IMAGE_PNG;
            }
        }
        return null;
    }

    private String uploadFile(String originalFilename, byte[] fileData) throws IOException {
        //랜덤한 이름 생성
        UUID uuid = UUID.randomUUID();
        //실제 저장될 파일명
        String createdFileName = uuid.toString() + "_" + originalFilename;
        //업로드 경로
        String uploadPath = shopProperties.getUploadPath();
        //파일생성
        File target = new File(uploadPath, createdFileName);
        //파일다운로드
        FileCopyUtils.copy(fileData, target);
        return createdFileName;
    }

}
