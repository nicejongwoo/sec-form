package com.sec.form.service;

import com.sec.form.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private ItemMapper serviceMapper;

}
