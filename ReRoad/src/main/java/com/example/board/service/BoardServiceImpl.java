package com.example.board.service;

import com.example.board.dao.BoardDao;
import com.example.board.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDao boardDao;

    @Override
    public List<BoardVo> retrieveList() {

        List<BoardVo> list =  this.boardDao.readAll();
        List<BoardFileVo> fileList = this.boardDao.thumnail();

        List<BoardVo> boardList = new ArrayList<>();

        for(BoardVo board : list){

            ArrayList<BoardFileVo> boardFileList = new ArrayList<BoardFileVo>();

           for(BoardFileVo file : fileList){
               if (board.getBoardNo() == file.getBoardNo()){
                   boardFileList.add(file);
               }
                    board.setBoardFiles(boardFileList);
           }
            boardList.add(board);
        }

        return boardList;
    }

    @Override
    public void updateUphit(int boardNo) {
        this.boardDao.upHit(boardNo);
    }

    @Override
    public List retrieveSearchWhole(String keyword) {
       List<BoardVo> list = this.boardDao.selectSearchWhole(keyword);

       List<BoardFileVo> fileList = this.boardDao.thumnail();

       List<BoardVo> boardVoList = new ArrayList<>();
       for(BoardVo board :  list){
           List<BoardFileVo> boardfileList = new ArrayList<>();
           for(BoardFileVo file : fileList){
               if(board.getBoardNo() == file.getBoardNo()){
                   boardfileList.add(file);

               }
               board.setBoardFiles(boardfileList);
           }
           boardVoList.add(board);
       }

       return boardVoList;
    }

    @Override
    public List retrieveSearchTitle(String keyword) {
        List<BoardVo> list = this.boardDao.selectSearchTitle(keyword);

        List<BoardFileVo> fileList = this.boardDao.thumnail();

        List<BoardVo> boardVoList = new ArrayList<>();
        for(BoardVo board :  list){
            List<BoardFileVo> boardfileList = new ArrayList<>();
            for(BoardFileVo file : fileList){
                if(board.getBoardNo() == file.getBoardNo()){
                    boardfileList.add(file);

                }
                board.setBoardFiles(boardfileList);
            }
            boardVoList.add(board);
        }

        return boardVoList;

    }

    @Override
    public List retrieveSearchContent(String keyword) {
        List<BoardVo> list = this.boardDao.selectSearchContent(keyword);
        List<BoardFileVo> fileList = this.boardDao.thumnail();

        List<BoardVo> boardVoList = new ArrayList<>();
        for(BoardVo board :  list){
            List<BoardFileVo> boardfileList = new ArrayList<>();
            for(BoardFileVo file : fileList){
                if(board.getBoardNo() == file.getBoardNo()){
                    boardfileList.add(file);

                }
                board.setBoardFiles(boardfileList);
            }
            boardVoList.add(board);
        }

        return boardVoList;
    }

    @Override
    public List retrieveSearchUserNick(String keyword) {
        List<BoardVo> list = this.boardDao.selectSearchUserNick(keyword);
        List<BoardFileVo> fileList = this.boardDao.thumnail();

        List<BoardVo> boardVoList = new ArrayList<>();
        for(BoardVo board :  list){
            List<BoardFileVo> boardfileList = new ArrayList<>();
            for(BoardFileVo file : fileList){
                if(board.getBoardNo() == file.getBoardNo()){
                    boardfileList.add(file);

                }
                board.setBoardFiles(boardfileList);
            }
            boardVoList.add(board);
        }

        return boardVoList;
    }

    @Override
    public BoardVo retrieveDetail(int boardNo) {
        BoardVo board = this.boardDao.selectDetailBoard(boardNo);

        List<BoardFileVo> boardFile = this.boardDao.thumnail();

        List<BoardFileVo> boardfileList = new ArrayList<>();
        for(BoardFileVo file : boardFile){
            if(boardNo == file.getBoardNo()){
                boardfileList.add(file);
            }
        }
        board.setBoardFiles(boardfileList);

        return board;
    }

    @Override
    public void remove(int boardNo) {
        this.boardDao.deleteBoard(boardNo);
    }

    @Override
    public void modifyBoard(BoardVo board) {
        this.boardDao.updateBoard(board);
    }

    @Override
    public void registerBoard(BoardVo board) {
        this.boardDao.insertBoard(board);
        int no = this.boardDao.lastId();
            for (BoardFileVo file : board.getBoardFiles()) {
                file.setBoardNo(no);
                this.boardDao.insertBoardFile(file);

        }
    }


    @Override
    public List retrieveComList(int boardNo) {
        List<CommentVo> list = this.boardDao.selectComList(boardNo);
        return list;

    }

    @Override
    public void registerComment(CommentVo comment) {
        this.boardDao.insertCom(comment);
    }

    @Override
    public void modifyComment(CommentVo comment) {
        this.boardDao.updateCom(comment);
    }

    @Override
    public void removeComment(int comNo) {
        this.boardDao.deleteCom(comNo);
    }

    @Override
    public int countCommemt(int boardNo) {
        return this.boardDao.selectComCount(boardNo);
    }

    @Override
    public void registerRecom(RecomVo recom) {
        this.boardDao.insertRecom(recom);
    }

    @Override
    public RecomVo retrieveRecom(RecomVo recom) {
        return this.boardDao.selectRecom(recom);
    }

    @Override
    public void removeRecom(RecomVo recom) {
        this.boardDao.deleteRecom(recom);
    }

    @Override
    public int ReComCount(int boardNo) {
        return this.boardDao.selectRecomCount(boardNo);
    }

    @Override
    public void registerReport(ReportVo report) {
        this.boardDao.insertReport(report);
    }

    @Override
    public ReportVo retrieveReport(ReportVo report) {
        return this.boardDao.selectReport(report);
    }

    @Override
    public List<ReportVo> retrieveReportList(String userId) {
        return this.boardDao.selectReportList(userId);
    }

    @Override
    public List<BoardVo> retrieveRecentBoardList(String userId) {
        return this.boardDao.readRecentList(userId);
    }

    @Override
    public List<BoardVo> retrieveUserBoardList(String userId) {
        return this.boardDao.readUserBoardList(userId);
    }

}
