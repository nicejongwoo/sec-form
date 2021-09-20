package com.sec.form.service;

import com.sec.form.domain.Item;
import com.sec.form.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemMapper itemMapper;

    @Override
    public void register(Item item) {
        this.itemMapper.create(item);
    }

    @Override
    public List<Item> list() {
        return itemMapper.list();
    }

    @Override
    public Item read(Integer itemId) {
        return itemMapper.read(itemId);
    }

    @Override
    public void modify(Item item) {
        this.itemMapper.update(item);
    }

    @Override
    public void remove(Item item) {
        this.itemMapper.delete(item);
    }

    @Override
    public String getPreview(Integer itemId) {
        return itemMapper.getPreview(itemId);
    }

    @Override
    public String getPicture(Integer itemId) {
        return itemMapper.getPicture(itemId);
    }
}
