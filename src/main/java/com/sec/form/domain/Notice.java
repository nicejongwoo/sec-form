package com.sec.form.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Notice {

    private int noticeNo;
    private String title;
    private String content;
    private LocalDateTime regDate;

}
