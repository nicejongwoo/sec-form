package com.sec.form.service;

import com.sec.form.domain.Board;
import com.sec.form.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public void register(Board board) {
        boardMapper.create(board);
    }

    @Override
    public List<Board> list() {
        return boardMapper.list();
    }

    @Override
    public Board read(int boardNo) {
        return boardMapper.read(boardNo);
    }

    @Override
    public void remove(int boardNo) {
        boardMapper.delete(boardNo);
    }

    @Override
    public void modify(Board board) {
        boardMapper.update(board);
    }

}
