package com.sec.form.mapper;

import com.sec.form.domain.Member;
import com.sec.form.domain.MemberAuth;

public interface MemberMapper {
    void create(Member member);

    void createAuth(MemberAuth memberAuth);
}
