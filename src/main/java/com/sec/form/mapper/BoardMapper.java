package com.sec.form.mapper;

import com.sec.form.domain.Board;

import java.util.List;

public interface BoardMapper {
    void create(Board board);

    List<Board> list();

    Board read(int boardNo);

    void delete(int boardNo);

    void update(Board board);
}
