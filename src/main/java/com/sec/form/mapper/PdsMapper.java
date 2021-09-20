package com.sec.form.mapper;

import com.sec.form.domain.Pds;

public interface PdsMapper {

    void create(Pds pds);

    void addAttach(String fullName);

}
