package com.sec.form.service;

import com.sec.form.mapper.CodeDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodeDetailServiceImpl implements CodeDetailService{

    private final CodeDetailMapper codeDetailMapper;

}
