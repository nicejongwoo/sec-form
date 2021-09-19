package com.sec.form.service;

import com.sec.form.domain.Board;

import java.util.List;

public interface BoardService {
    void register(Board board);

    List<Board> list();

    Board read(int boardNo);

    void remove(int boardNo);

    void modify(Board board);
}
