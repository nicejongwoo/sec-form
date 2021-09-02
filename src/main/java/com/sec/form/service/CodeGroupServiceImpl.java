package com.sec.form.service;

import com.sec.form.mapper.CodeGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodeGroupServiceImpl implements CodeGroupService{

    private final CodeGroupMapper codeGroupMapper;

}
