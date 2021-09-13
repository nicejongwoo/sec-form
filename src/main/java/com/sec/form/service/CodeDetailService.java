package com.sec.form.service;

import com.sec.form.domain.CodeDetail;

import java.util.List;

public interface CodeDetailService {

    void register(CodeDetail codeDetail);

    List<CodeDetail> list();

    CodeDetail read(CodeDetail codeDetail);
}
