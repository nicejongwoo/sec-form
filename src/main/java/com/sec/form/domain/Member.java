package com.sec.form.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class Member {

    private int userNo;

    @NotBlank
    private String userId;

    @NotBlank
    private String userPw;

    @NotBlank
    private String userName;

    private String job;
    private int coin;

    private boolean enabled;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private List<MemberAuth> authList;

}
