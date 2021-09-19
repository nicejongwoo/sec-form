package com.sec.form.service;

import com.sec.form.domain.Notice;
import com.sec.form.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;

    @Override
    public void register(Notice notice) {
        noticeMapper.create(notice);
    }

    @Override
    public List<Notice> list() {
        return noticeMapper.list();
    }

    @Override
    public Notice read(int noticeNo) {
        return noticeMapper.read(noticeNo);
    }

    @Override
    public void modify(Notice notice) {
        noticeMapper.update(notice);
    }

    @Override
    public void remove(int noticeNo) {
        noticeMapper.delete(noticeNo);
    }
}
