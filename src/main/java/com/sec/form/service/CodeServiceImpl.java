package com.sec.form.service;

import com.sec.form.domain.CodeLabelValue;
import com.sec.form.mapper.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {

    private final CodeMapper codeMapper;

    @Override
    public List<CodeLabelValue> getCodeGroupList() {
        return codeMapper.getCodeGroupList();
    }
}
