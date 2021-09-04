package com.sec.form.service;

import com.sec.form.domain.CodeGroup;
import com.sec.form.mapper.CodeGroupMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CodeGroupServiceImplTest {

    @InjectMocks
    private CodeGroupServiceImpl codeGroupService;

    @Mock
    private CodeGroupMapper codeGroupMapper;

    @DisplayName("register 서비스 테스트")
    @Test
    void registerTest(){
        CodeGroup mockCodeGroup = new CodeGroup();
        mockCodeGroup.setGroupCode("001");
        mockCodeGroup.setGroupName("codename");
        codeGroupService.register(mockCodeGroup);
        verify(codeGroupMapper, times(1)).create(mockCodeGroup);
    }


    @DisplayName("codeGroup list service test")
    @Test
    void listTest(){
        List<CodeGroup> mockList = new ArrayList<>();

        CodeGroup mockCodeGroup = new CodeGroup();
        mockCodeGroup.setGroupCode("001");
        mockCodeGroup.setGroupName("codename001");
        CodeGroup mockCodeGroup2 = new CodeGroup();
        mockCodeGroup2.setGroupCode("002");
        mockCodeGroup2.setGroupName("codename002");

        mockList.add(0, mockCodeGroup);
        mockList.add(1, mockCodeGroup2);

        //의존하는 mock객체에서 해당 리스트를 받아 왔다고 가정하고 값을 정함
        given(codeGroupMapper.list()).willReturn(mockList);

        List<CodeGroup> list = codeGroupService.list();
        CodeGroup codeGroup = list.get(0);
        assertEquals("codename001", codeGroup.getGroupName());
        assertEquals(2, list.size());

    }

}