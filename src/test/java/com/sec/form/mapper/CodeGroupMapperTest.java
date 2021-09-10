package com.sec.form.mapper;

import com.sec.form.domain.CodeGroup;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void beforeEach() {
        CodeGroup codeGroup = new CodeGroup();
        codeGroup.setGroupCode("001");
        codeGroup.setGroupName("codename");
        codeGroupMapper.create(codeGroup);
    }

    @DisplayName("register_mapper_test")
    @Test
    void registerMapperTest() {
        CodeGroup savedData = codeGroupMapper.read("001");
        assertEquals("001", savedData.getGroupCode());
    }

    @DisplayName("list_mapper_test")
    @Test
    void listMapperTest(){
        List<CodeGroup> list = codeGroupMapper.list();
        assertEquals(1, list.size());
    }

}