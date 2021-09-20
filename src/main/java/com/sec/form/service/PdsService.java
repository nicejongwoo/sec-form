package com.sec.form.service;

import com.sec.form.domain.Pds;

import java.util.List;

public interface PdsService {

    void register(Pds pds);

    List<Pds> list();

}
