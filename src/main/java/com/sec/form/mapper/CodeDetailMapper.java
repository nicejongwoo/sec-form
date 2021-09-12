package com.sec.form.mapper;

import com.sec.form.domain.CodeDetail;

public interface CodeDetailMapper {
    void create(CodeDetail codeDetail);

    int getMaxSortSeq(String groupCode);
}
