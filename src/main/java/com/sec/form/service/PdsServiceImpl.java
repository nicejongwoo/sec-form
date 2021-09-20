package com.sec.form.service;

import com.sec.form.mapper.PdsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PdsServiceImpl implements PdsService{

    private final PdsMapper pdsMapper;
}
