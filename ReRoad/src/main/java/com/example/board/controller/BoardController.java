package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardFileVo;
import com.example.board.vo.BoardVo;
import com.example.board.vo.CommentVo;
import com.example.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(@AuthenticationPrincipal User principal, Model model){

        List<BoardVo> list = this.boardService.retrieveList();



        model.addAttribute("boardList",list);
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roles", principal.getAuthorities());

        return "views/board/boardList";
    }

    @GetMapping("/detailBoard/{boardNo}")
    public String boardDetail(@PathVariable int boardNo, Model model, @AuthenticationPrincipal User principal){
        this.boardService.updateUphit(boardNo);
        BoardVo board = boardService.retrieveDetail(boardNo);
        List<CommentVo> comlist = this.boardService.retrieveComList(boardNo);

        int recomCount = this.boardService.ReComCount(boardNo);
        board.setRecomCount(recomCount);

        log.info("board dd :"+board);

        model.addAttribute("username", principal.getUsername());
        model.addAttribute("roles", principal.getAuthorities());
        model.addAttribute("board", board);
        model.addAttribute("commentList", comlist);

        return "views/board/detailBoard";
    }

    @PostMapping("/deleteBoard/{boardNo}")
    public String boardDelete(@PathVariable int boardNo ){

        this.boardService.remove(boardNo);

        return "redirect:/boardList";
    }

    @GetMapping("/modifyBoardForm/{boardNo}")
    public String boardModifyForm(@PathVariable int boardNo, Model model, @AuthenticationPrincipal User principal){

        BoardVo board = boardService.retrieveDetail(boardNo);
        model.addAttribute("board", board);
        return "views/board/modifyBoard";
    }

    @PostMapping("/modifyboard")
    public String boardModify(BoardVo board){
        int boardNo = board.getBoardNo();

        this.boardService.modifyBoard(board);
        return "redirect:/detailBoard/"+ boardNo;
    }

    @GetMapping("/writeboardForm")
    public String boardWriteForm(){

        return "views/board/boardWrite";
    }

    @PostMapping("/boardWrite")
    public String boardWrtie(@ModelAttribute("board") @Valid BoardVo board,
                             BindingResult bindingResult, Model model,
                             RedirectAttributes attributes, @AuthenticationPrincipal User principal) throws Exception{
             board.setUserId(principal.getUsername());

             System.out.println("!board.getFileList() " +board.getFileList());

            if(!board.getFileList().isEmpty()){

                    List<BoardFileVo> boardFile = FileUtils.uploadFiles(board.getFileList());

                    board.setBoardFiles(boardFile);

            }

        this.boardService.registerBoard(board);

        return "redirect:/boardList";
        }
    }

