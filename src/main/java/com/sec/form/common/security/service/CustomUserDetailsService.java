package com.sec.form.service;

import com.sec.form.common.security.domain.CustomUser;
import com.sec.form.domain.Member;
import com.sec.form.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("loadUserByUsername= {}", userId);
        Member member = memberMapper.readByUserId(userId);

        return member == null ? null : new CustomUser(member);
    }
}
