package com.example.board.dao;

import com.example.board.vo.BoardFileVO;
import com.example.board.vo.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    void insertBoard(BoardVO board);
    void insertBoardFile(BoardFileVO file);
    void readAll(Map map);

    int lastId();
}
