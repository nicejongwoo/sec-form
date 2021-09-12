package com.sec.form.service;

import com.sec.form.domain.CodeGroup;

import java.util.List;

public interface CodeGroupService {
    void register(CodeGroup codeGroup);

    List<CodeGroup> list();

    CodeGroup read(String groupCode);

    void modify(CodeGroup codeGroup);

    void remove(String groupCode);
}
