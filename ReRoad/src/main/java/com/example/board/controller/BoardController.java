package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVo;
import com.example.board.vo.CommentVo;
import com.example.member.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(Model model){

        List<BoardVo> list = this.boardService.retrieveList();

        model.addAttribute("boardList",list);


        return "views/board/boardList";
    }

    @GetMapping("/detailBoard/{boardNo}")

    public String boardDetail(@PathVariable int boardNo, Model model,Authentication authentication, HttpSession session){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String userId = userDetails.getUsername();


        BoardVo board = boardService.retrieveDetail(boardNo);
        board.setUserId(userId);

        List<CommentVo> comlist = this.boardService.retrieveComList(boardNo);
        System.out.println(board);
        model.addAttribute("board", board);
        model.addAttribute("commentList", comlist);
        return "views/board/detailBoard";
    }
}
