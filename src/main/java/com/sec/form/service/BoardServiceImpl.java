package com.sec.form.service;

import com.sec.form.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl {

    private final BoardMapper boardMapper;

}
