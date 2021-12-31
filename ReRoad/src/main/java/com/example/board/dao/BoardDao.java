package com.example.board.dao;

import com.example.board.vo.BoardVo;
import com.example.board.vo.CommentVo;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    //목록보기
    List<BoardVo> readAll();

    //게시글 검색
    List selectSearchWhole(String keyword);
    List selectSearchTitle(String keyword);
    List selectSearchUserNick(String keyword);
    List selectSearchContent(String keyword);

    //게시글 상세조회
    BoardVo selectDetailBoard(int boardNo);

    //댓글
    List selectComList(int boardNo);
    void insertCom(CommentVo comment);
    void updateCom(CommentVo comment);

}