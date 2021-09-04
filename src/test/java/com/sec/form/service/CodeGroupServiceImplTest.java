package com.sec.form.service;

import com.sec.form.domain.CodeGroup;
import com.sec.form.mapper.CodeGroupMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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

}