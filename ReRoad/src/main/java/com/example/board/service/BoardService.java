package com.example.board.service;

import com.example.board.vo.BoardVo;
import com.example.board.vo.CommentVo;


import java.util.List;
import java.util.Map;


public interface BoardService {

    //게시글 목록보기
    List<BoardVo> retrieveList();

    //게시글 검색
    List retrieveSearchWhole(String keyword);
    List retrieveSearchTitle(String keyword);
    List retrieveSearchContent(String keyword);
    List retrieveSearchUserNick(String keyword);

    //게시글 상세보기
    BoardVo retrieveDetail(int boardNo);

    //댓글
    List retrieveComList(int boardNo);
    void registerComment(CommentVo comment);
    void modifyComment(CommentVo comment);

}
