package com.sec.form.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CodeDetail {

    private String groupCode;
    private String codeValue;
    private String codeName;
    private int sortSeq;
    private String useYn;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
