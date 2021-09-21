package com.sec.form.mapper;

import com.sec.form.domain.Pds;

import java.util.List;

public interface PdsMapper {

    void create(Pds pds);

    void addAttach(String fullName);

    List<Pds> list();

    Pds read(Integer itemId);

    List<String> getAttach(Integer itemId);

    void update(Pds pds);

    void updateAttach(String fullName, Integer itemId);

}
