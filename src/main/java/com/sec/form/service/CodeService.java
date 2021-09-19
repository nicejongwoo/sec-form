package com.sec.form.service;

import com.sec.form.domain.CodeLabelValue;

import java.util.List;

public interface CodeService {

    List<CodeLabelValue> getCodeGroupList();

    List<CodeLabelValue> getCodeList(String groupCode);
}
