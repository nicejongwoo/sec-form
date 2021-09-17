package com.sec.form.mapper;

import com.sec.form.domain.CodeDetail;

import java.util.List;

public interface CodeDetailMapper {
    void create(CodeDetail codeDetail);

    int getMaxSortSeq(String groupCode);

    List<CodeDetail> list();

    CodeDetail read(CodeDetail codeDetail);

    void update(CodeDetail codeDetail);
}
