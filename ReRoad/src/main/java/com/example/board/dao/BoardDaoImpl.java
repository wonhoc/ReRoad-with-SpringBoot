package com.example.board.dao;

import com.example.board.vo.BoardVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<BoardVo> readAll() {

        List<BoardVo> list = this.sqlSession.selectList("Board.selectList");
        return list;
    }

    @Override
    public List selectSearchWhole(String keyword) {
        List<BoardVo> list = this.sqlSession.selectList("Board.searchWhole", keyword);
        return list;
    }

    @Override
    public List selectSearchTitle(String keyword) {
        List<BoardVo> list = this.sqlSession.selectList("Board.searchTitle",keyword);
        return list;
    }

    @Override
    public List selectSearchUserNick(String keyword) {
        List<BoardVo> list = this.sqlSession.selectList("Board.searchUserNick", keyword);
        return list;
    }

    @Override
    public List selectSearchContent(String keyword) {
        List<BoardVo> list = this.sqlSession.selectList("Board.searchContent",keyword);
        return list;
    }

    @Override
    public BoardVo selectDetailBoard(int boardNo) {
        BoardVo board = this.sqlSession.selectOne("Board.boardDetail", boardNo);


        return board;

    }
}
