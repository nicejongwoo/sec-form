package com.sec.form.service;

import com.sec.form.domain.Pds;
import com.sec.form.mapper.PdsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<Pds> list() {
        return pdsMapper.list();
    }

    @Override
    public Pds read(Integer itemId) {
        return pdsMapper.read(itemId);
    }

    @Override
    public List<String> getAttach(Integer itemId) {
        return pdsMapper.getAttach(itemId);
    }

    @Transactional
    @Override
    public void modify(Pds pds) {
        pdsMapper.update(pds);

        String[] files = pds.getFiles();
        if (files == null) {
            return;
        }

        //기존 DB값 조회
        List<String> attach = pdsMapper.getAttach(pds.getItemId());

        //기존에 들어있던 자료는 그대로 두고 새로 첨부하는 목록만 저장
        for (String fullName : files) {
            for (String file : attach) {
                if (!fullName.equals(file)) {
                    pdsMapper.updateAttach(fullName, pds.getItemId());
                }
            }
        }
    }

}
