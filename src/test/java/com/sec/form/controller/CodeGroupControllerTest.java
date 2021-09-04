package com.sec.form.controller;

import com.sec.form.service.CodeGroupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    void registerFormTest() throws Exception {
        mockMvc.perform(get("/codegroup/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("codegroup/register"))
                .andExpect(model().attributeExists("codeGroup"));
    }


    @DisplayName("codegroup 등록 - 정상")
    @Test
    void registerTest() throws Exception {
        mockMvc.perform(post("/codegroup/register")
                        .param("groupCode", "001")
                        .param("groupName", "codename"))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("msg"))
                .andExpect(redirectedUrl("/codegroup/list"));

        verify(codeGroupService, times(1)).register(any());
    }


    @DisplayName("리스트")
    @Test
    void listTest() throws Exception {
        mockMvc.perform(get("/codegroup/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("codegroup/list"));
    }


}