package com.sec.form.mapper;

import com.sec.form.domain.Item;

import java.util.List;

public interface ItemMapper {
    void create(Item item);

    List<Item> list();

    Item read(Integer itemId);

    void update(Item item);

    void delete(Item item);

    String getPreview(Integer itemId);

    String getPicture(Integer itemId);
}
