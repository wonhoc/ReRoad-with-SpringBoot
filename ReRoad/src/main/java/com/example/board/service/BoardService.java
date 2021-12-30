package com.example.board.service;

import com.example.board.vo.BoardFileVO;
import com.example.board.vo.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    //게시판
    void createBoard(BoardVO board);

    //파일
    void createFile(BoardFileVO file);

    //게시판 리스트
    void retrieveBoardList (Map map);

    int lastId();
}
