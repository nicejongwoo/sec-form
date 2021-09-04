package com.sec.form.controller;

import com.sec.form.service.CodeGroupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMybatis
@WebMvcTest(CodeGroupController.class)
class CodeGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CodeGroupService codeGroupService;

    @DisplayName("codegroup 등록 폼")
    @Test
    void test() throws Exception {
        mockMvc.perform(get("/codegroup/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("codegroup/register"))
                .andExpect(model().attributeExists("codeGroup"));
    }



}