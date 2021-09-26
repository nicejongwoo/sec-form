package com.sec.form.service;

import com.sec.form.domain.Notice;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface NoticeService {
    @PreAuthorize("hasRole('ADMIN')")
    void register(Notice notice);

    List<Notice> list();

    Notice read(int noticeNo);

    @PreAuthorize("hasRole('ADMIN')")
    void modify(Notice notice);

    @PreAuthorize("hasRole('ADMIN')")
    void remove(int noticeNo);
}


