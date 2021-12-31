package com.example.board.service;

import com.example.board.dao.BoardDao;
import com.example.board.vo.BoardVo;

import com.example.board.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDao boardDao;

    @Override
    public List<BoardVo> retrieveList() {
        return this.boardDao.readAll();
    }

    @Override
    public List retrieveSearchWhole(String keyword) {
       List<BoardVo> list = this.boardDao.selectSearchWhole(keyword);
       return list;
    }

    @Override
    public List retrieveSearchTitle(String keyword) {
        List<BoardVo> list = this.boardDao.selectSearchTitle(keyword);
        return list;

    }

    @Override
    public List retrieveSearchContent(String keyword) {
        List<BoardVo> list = this.boardDao.selectSearchContent(keyword);
        return list;
    }

    @Override
    public List retrieveSearchUserNick(String keyword) {
        List<BoardVo> list = this.boardDao.selectSearchUserNick(keyword);
        return list;
    }

    @Override
    public BoardVo retrieveDetail(int boardNo) {
        BoardVo board = this.boardDao.selectDetailBoard(boardNo);
        return board;
    }

    @Override
    public List retrieveComList(int boardNo) {
        List<CommentVo> list = this.boardDao.selectComList(boardNo);
        return list;

    }
}
