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
        pdsMapper.updateViewCnt(itemId);
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
        //기존 DB값 삭제
        for (String fullName : attach) {
            pdsMapper.deleteByFullName(fullName);
        }

        //저장
        for (String fullName : files) {
            pdsMapper.updateAttach(fullName, pds.getItemId());
        }
    }

    @Transactional
    @Override
    public void remove(Integer itemId) {
        pdsMapper.deleteAttach(itemId);
        pdsMapper.delete(itemId);
    }

    @Override
    public void updateAttachDownCnt(String fullName) {
        pdsMapper.updateAttachDownCnt(fullName);
    }

}
