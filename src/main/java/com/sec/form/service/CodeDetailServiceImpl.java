package com.sec.form.service;

import com.sec.form.domain.CodeDetail;
import com.sec.form.mapper.CodeDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodeDetailServiceImpl implements CodeDetailService{

    private final CodeDetailMapper codeDetailMapper;

    @Override
    public void register(CodeDetail codeDetail) {
        String groupCode = codeDetail.getGroupCode();
        int maxSortSeq = codeDetailMapper.getMaxSortSeq(groupCode);
        codeDetail.setSortSeq(maxSortSeq);
        codeDetailMapper.create(codeDetail);
    }
}
