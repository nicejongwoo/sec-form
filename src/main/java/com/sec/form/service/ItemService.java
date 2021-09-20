package com.sec.form.service;

import com.sec.form.domain.Item;

import java.util.List;

public interface ItemService {
    void register(Item item);

    List<Item> list();

    Item read(Integer itemId);

    void modify(Item item);

    void remove(Item item);

    String getPreview(Integer itemId);

    String getPicture(Integer itemId);
}
