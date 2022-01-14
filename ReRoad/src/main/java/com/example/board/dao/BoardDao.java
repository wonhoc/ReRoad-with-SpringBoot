package com.example.board.dao;

import com.example.board.vo.*;

import java.util.List;

public interface BoardDao {
    //목록보기
    List<BoardVo> readAll();//
    void upHit(int boardNo);//

    //게시글 검색
    List selectSearchWhole(String keyword);//
    List selectSearchTitle(String keyword);//
    List selectSearchUserNick(String keyword);//
    List selectSearchContent(String keyword);//

    //게시글
    BoardVo selectDetailBoard(int boardNo);//
    void deleteBoard(int boardNo);//
    void updateBoard(BoardVo board);//
    void insertBoard(BoardVo board);//

    //파일
    void insertBoardFile(BoardFileVo boardFile);//
    int lastId();
    List<BoardFileVo> thumnail();//

    //댓글
    List selectComList(int boardNo);//
    void insertCom(CommentVo comment);//
    void updateCom(CommentVo comment);//
    void deleteCom(int comNo);//
    int selectComCount(int boardNo);

    //추천
    void insertRecom(RecomVo recom);//
    RecomVo selectRecom(RecomVo recom);//
    void deleteRecom(RecomVo recom);//
    int selectRecomCount(int boardNo);//

    //신고
    void insertReport(ReportVo report);//
    ReportVo selectReport(ReportVo report);//
    List<ReportVo> selectReportList(String userId);//

    //회원 게시글 보기
    List<BoardVo> readRecentList(String userId);
    List<BoardVo> readUserBoardList(String userId);


}
