package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVo;
import com.example.board.vo.CommentVo;
import com.example.board.vo.RecomVo;
import com.example.board.vo.ReportVo;
import com.example.common.SearchVO;
import com.example.member.vo.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/search")
    public Map searchBoardLi(@RequestBody SearchVO search){
        Map<String, Object> map = new HashMap<String, Object>();
        String keyfield = search.getKeyfield();

        List<BoardVo> list = new ArrayList<BoardVo>();

        if(keyfield.equals("all")){
            list = this.boardService.retrieveSearchWhole(search.getKeyword());
        }else if(keyfield.equals("boardTitle")){
            list =this.boardService.retrieveSearchTitle(search.getKeyword());
        }else if(keyfield.equals("boardContent")){
            list = this.boardService.retrieveSearchContent(search.getKeyword());
        }else if(keyfield.equals("userNick")){
            list = this.boardService.retrieveSearchUserNick(search.getKeyword());
        }

        System.out.println("list + "+ list);

        map.put("results",list);
        return map;
    }

    @PostMapping("/createComment")
    public Map createComment(@RequestBody CommentVo comment, @AuthenticationPrincipal UserAccount user){
        HashMap<String, Object> map = new HashMap<String, Object>();
        int boardNo = comment.getBoardNo();

        String UserId = user.getUser().getUserId();
        String userNick = user.getUser().getUserNick();

        comment.setUserId(UserId);
        comment.setUserNick(userNick);

        this.boardService.registerComment(comment);
        List<CommentVo> list = this.boardService.retrieveComList(boardNo);

        map.put("results",list);
        return map;
    }

    @PostMapping("/modityComment")
    public Map updateComment(@RequestBody CommentVo comment, @AuthenticationPrincipal User principal){
        HashMap<String, Object> map = new HashMap<String, Object>();
        int boardNo = comment.getBoardNo();
        comment.setUserId(principal.getUsername());

        System.out.println("commentgetComNo : "+comment.getComNo());
        this.boardService.modifyComment(comment);
        List<CommentVo> list = this.boardService.retrieveComList(boardNo);
        map.put("results",list);
        return map;
    }

    @PostMapping("/removeComment")
    public Map deleteComment(@RequestBody CommentVo comment, @AuthenticationPrincipal User principal){
        HashMap<String, Object> map = new HashMap<String, Object>();
        int boardNo = comment.getBoardNo();
        comment.setUserId(principal.getUsername());
        System.out.println("commentgetComNo : "+comment.getComNo());

        this.boardService.removeComment(comment.getComNo());
        List<CommentVo> list = this.boardService.retrieveComList(boardNo);
        map.put("results",list);
        return map;
    }

    @PostMapping("/recommend")
    public Map recommend(@RequestBody RecomVo recom, @AuthenticationPrincipal User pricipal){
        HashMap<String, Object> map = new HashMap<String, Object>();
        recom.setUserId(pricipal.getUsername());
        RecomVo isRecom = this.boardService.retrieveRecom(recom);
        int boardNo = recom.getBoardNo();

        if(isRecom == null){
            this.boardService.registerRecom(recom);
            int recomCount = this.boardService.ReComCount(boardNo);
            map.put("results", "추천을 완료했습니다.");
            map.put("count", recomCount);
        }else if(isRecom != null){
            this.boardService.removeRecom(recom);
            map.put("results", "추천을 취소하셨습니다.");
            int recomCount = this.boardService.ReComCount(boardNo);
            map.put("count", recomCount);
        }else {
            System.out.println("오류");
        }
        System.out.println("map : "+map);
        return map;
    }

    @PostMapping("/report")
    public Map report(@RequestBody ReportVo report, @AuthenticationPrincipal User pricipal){
        HashMap<String, Object> map = new HashMap<String, Object>();
        report.setReporter(pricipal.getUsername());

        ReportVo isReported = this.boardService.retrieveReport(report);
        int boardNo = report.getBoardNo();
        if(isReported == null){
            this.boardService.registerReport(report);
            map.put("results", "신고 완료했습니다.");
        }else if(isReported != null){
            map.put("results", "이미 신고하셨습니다.");

        }
            return map;
    }
}
