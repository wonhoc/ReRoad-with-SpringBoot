package com.example.board.dao;

import com.example.board.vo.BoardFileVO;
import com.example.board.vo.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{

    @Autowired
    private SqlSession sqlSession;

    //게시글 작성
    @Override
    public void insertBoard(BoardVO board) {
        this.sqlSession.insert("Board.insertBoardCall", board);
    }

    //파일 등록
    @Override
    public void insertBoardFile(BoardFileVO file) {
        this.sqlSession.insert("Board.insertBoardFileCall", file);
    }

    //게시글 목록보기
    @Override
    public void readAll(Map map) {
    List<BoardVO> list = this.sqlSession.selectList("Board.selectCardBoardList");
    map.put("results",list);
    }

    @Override
    public int lastId() {
        return this.sqlSession.selectOne("Board.lastId");
    }
}
