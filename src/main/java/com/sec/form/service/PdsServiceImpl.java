package com.sec.form.service;

import com.sec.form.domain.Pds;
import com.sec.form.mapper.PdsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PdsServiceImpl implements PdsService{

    private final PdsMapper pdsMapper;

    @Transactional
    @Override
    public void register(Pds pds) {
        pdsMapper.create(pds);
        //첨부
        String[] files = pds.getFiles();
        if (files == null) {
            return;
        }
        for (String fullName : files) {
            pdsMapper.addAttach(fullName);
        }
    }
}
