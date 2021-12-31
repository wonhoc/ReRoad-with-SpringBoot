package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVo;
import com.example.board.vo.CommentVo;
import com.example.member.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(@AuthenticationPrincipal User principal, Model model){

        System.out.println("권한"+principal.getAuthorities());
        System.out.println("이름" +principal.getUsername());


        List<BoardVo> list = this.boardService.retrieveList();

        model.addAttribute("boardList",list);
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roles", principal.getAuthorities());

        return "views/board/boardList";
    }

    @GetMapping("/detailBoard/{boardNo}")

    public String boardDetail(@PathVariable int boardNo, Model model, @AuthenticationPrincipal User principal){

        BoardVo board = boardService.retrieveDetail(boardNo);

        List<CommentVo> comlist = this.boardService.retrieveComList(boardNo);

        System.out.println("comlist : " + comlist);

        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roles", principal.getAuthorities());
        model.addAttribute("board", board);
        model.addAttribute("commentList", comlist);

        return "views/board/detailBoard";
    }
}
