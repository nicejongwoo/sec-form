package com.sec.form.mapper;

import com.sec.form.domain.Notice;

import java.util.List;

public interface NoticeMapper {
    void create(Notice notice);

    List<Notice> list();

    Notice read(int noticeNo);

    void update(Notice notice);

    void delete(int noticeNo);
}
