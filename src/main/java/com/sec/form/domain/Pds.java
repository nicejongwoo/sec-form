package com.sec.form.domain;

import lombok.Data;

@Data
public class Pds {

    private Integer itemId;
    private String itemName;
    private String description;
    private String[] files;
    private Integer viewCnt;

}
