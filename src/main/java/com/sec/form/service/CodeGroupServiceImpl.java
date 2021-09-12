package com.sec.form.service;

import com.sec.form.domain.CodeGroup;
import com.sec.form.mapper.CodeGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CodeGroupServiceImpl implements CodeGroupService{

    private final CodeGroupMapper codeGroupMapper;

    @Override
    public void register(CodeGroup codeGroup) {
        codeGroupMapper.create(codeGroup);
    }

    @Override
    public List<CodeGroup> list() {
        return codeGroupMapper.list();
    }

    @Override
    public CodeGroup read(String groupCode) {
        return codeGroupMapper.read(groupCode);
    }

    @Override
    public void modify(CodeGroup codeGroup) {
        codeGroupMapper.update(codeGroup);
    }

    @Override
    public void remove(String groupCode) {
        codeGroupMapper.delete(groupCode);
    }
}
