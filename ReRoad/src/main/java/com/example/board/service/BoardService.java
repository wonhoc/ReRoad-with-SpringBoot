package com.example.board.service;

import com.example.board.vo.*;


import java.util.List;
import java.util.Map;


public interface BoardService {

    //게시글 목록보기
    List<BoardVo> retrieveList();
    void updateUphit(int boardNo);

    //게시글 검색
    List retrieveSearchWhole(String keyword);
    List retrieveSearchTitle(String keyword);
    List retrieveSearchContent(String keyword);
    List retrieveSearchUserNick(String keyword);

    //게시글
    BoardVo retrieveDetail(int boardNo);
    void remove(int boardNo);
    void modifyBoard(BoardVo board);
    void registerBoard(BoardVo board);



    //댓글
    List retrieveComList(int boardNo);
    void registerComment(CommentVo comment);
    void modifyComment(CommentVo comment);
    void removeComment(int comNo);
    int countCommemt(int boardNo);

    //추천
    void registerRecom(RecomVo recom);
    RecomVo retrieveRecom(RecomVo recom);
    void removeRecom(RecomVo recom);
    int ReComCount(int boardNo);

    //신고
    void registerReport(ReportVo report);
    ReportVo retrieveReport(ReportVo report);
    List<ReportVo> retrieveReportList(String userId);

    //회원 게시글 보기
    List<BoardVo> retrieveRecentBoardList(String userId);
    List<BoardVo> retrieveUserBoardList(String userId);

    //회원 댓글 보기
    List<CommentVo> retrieveRecentComList(String userId);

}
