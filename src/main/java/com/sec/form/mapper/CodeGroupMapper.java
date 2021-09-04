package com.sec.form.mapper;

import com.sec.form.domain.CodeGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface CodeGroupMapper {
    void create(CodeGroup codeGroup);

    CodeGroup read(String groupCode);

    List<CodeGroup> list();

}
