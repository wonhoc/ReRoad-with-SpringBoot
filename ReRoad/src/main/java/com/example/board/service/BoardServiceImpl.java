package com.example.board.service;

import com.example.board.dao.BoardDao;
import com.example.board.vo.BoardFileVO;
import com.example.board.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDao boardDao;


    @Override
    public void createBoard(BoardVO board) {
        this.boardDao.insertBoard(board);


    }

    @Override
    public void createFile(BoardFileVO file) {
        this.boardDao.insertBoardFile(file);
    }

    @Override
    public void retrieveBoardList(Map map) {
        this.boardDao.readAll(map);
    }

    //게시판 리스트


    @Override
    public int lastId() {
        return this.boardDao.lastId();
    }
}
