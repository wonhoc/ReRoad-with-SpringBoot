package com.example.board.dao;

import com.example.board.vo.*;

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
    public void upHit(int boardNo) {
        this.sqlSession.update("Board.boardUphit", boardNo);
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

    @Override
    public void deleteBoard(int boardNo) {
        this.sqlSession.delete("Board.boardDelete", boardNo);
    }

    @Override
    public void updateBoard(BoardVo board) {
        this.sqlSession.update("Board.boardModify",board);
    }

    @Override
    public void insertBoard(BoardVo board) {
        this.sqlSession.insert("Board.boardWrite", board);
    }

    @Override
    public void insertBoardFile(BoardFileVo boardFile) {

        this.sqlSession.insert("Board.boardFileInsert", boardFile);
    }


    @Override
    public int lastId() {
        return this.sqlSession.selectOne("Board.lastId");
    }

    @Override
    public List<BoardFileVo> thumnail(int boardNo) {
        return this.sqlSession.selectList("Board.boardFileThumnail", boardNo);
    }

    @Override
    public List selectComList(int boardNo) {
        List<CommentVo> list = this.sqlSession.selectList("Board.commmentList",boardNo);
        return list;
    }

    @Override
    public void insertCom(CommentVo comment) {
        this.sqlSession.insert("Board.commmentInsert", comment);
    }

    @Override
    public void updateCom(CommentVo comment) {
        this.sqlSession.update("Board.commentUpdate", comment);
    }

    @Override
    public void deleteCom(int comNo) {
        this.sqlSession.delete("Board.commentDelete", comNo);
    }

    @Override
    public void insertRecom(RecomVo recom) {
        this.sqlSession.update("Board.recomUpdate", recom);

    }

    @Override
    public RecomVo selectRecom(RecomVo recom) {

        return this.sqlSession.selectOne("Board.recomSelect", recom);
    }

    @Override
    public void deleteRecom(RecomVo recom) {
        this.sqlSession.delete("Board.recomDelete", recom);
    }

    @Override
    public int selectRecomCount(int boardNo) {
        return this.sqlSession.selectOne("Board.recomCount", boardNo);
    }

    @Override
    public void insertReport(ReportVo report) {
        this.sqlSession.update("Board.reportUpdate", report);
    }

    @Override
    public ReportVo selectReport(ReportVo report) {
        return this.sqlSession.selectOne("Board.reportSelect", report);
    }
}
