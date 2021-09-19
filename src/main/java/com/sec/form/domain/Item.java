package com.sec.form.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Item {

    private Integer itemId;

    private String itemName;

    private Integer price;

    private String description;

    private MultipartFile picture;

    private String pictureUrl;

    private MultipartFile preview;

    private String previewUrl;

}
