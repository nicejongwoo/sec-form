package com.sec.form.mapper;

import com.sec.form.domain.CodeGroup;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface CodeGroupMapper {
    void create(CodeGroup codeGroup);

    CodeGroup read(String groupCode);
}
