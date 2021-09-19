package com.sec.form.service;

import com.sec.form.domain.Member;
import com.sec.form.domain.MemberAuth;
import com.sec.form.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public void register(Member member) {
        memberMapper.create(member);

        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setUserNo(member.getUserNo());
        memberAuth.setAuth("ROLE_MEMBER");

        memberMapper.createAuth(memberAuth);
    }
}
