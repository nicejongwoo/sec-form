package com.sec.form.mapper;

import com.sec.form.domain.CodeGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class CodeGroupMapperTest {

    @Autowired
    private CodeGroupMapper codeGroupMapper;

    @DisplayName("register_mapper_test")
    @Test
    void registerMapperTest() {
        CodeGroup codeGroup = new CodeGroup();
        codeGroup.setGroupCode("001");
        codeGroup.setGroupName("codename");
        codeGroupMapper.create(codeGroup);
        CodeGroup savedData = codeGroupMapper.read(codeGroup.getGroupCode());
        assertEquals(codeGroup.getGroupCode(), savedData.getGroupCode());
    }

    @DisplayName("list_mapper_test")
    @Test
    void listMapperTest(){
        CodeGroup codeGroup = new CodeGroup();
        codeGroup.setGroupCode("001");
        codeGroup.setGroupName("codename");
        codeGroupMapper.create(codeGroup);
        List<CodeGroup> list = codeGroupMapper.list();
        assertEquals(1, list.size());
    }

}