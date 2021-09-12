package com.sec.form.service;

import com.sec.form.mapper.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {

    private final CodeMapper codeMapper;

}
