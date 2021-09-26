package com.sec.form.service;

import com.sec.form.domain.Notice;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
public interface NoticeService {
    void register(Notice notice);

    List<Notice> list();

    Notice read(int noticeNo);

    void modify(Notice notice);

    void remove(int noticeNo);
}
