package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardFileVo;
import com.example.board.vo.BoardVo;
import com.example.board.vo.CommentVo;
import com.example.board.vo.ReportVo;
import com.example.member.vo.UserAccount;
import com.example.member.vo.UserVo;
import com.example.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(@AuthenticationPrincipal User principle, Model model) {

        List<BoardVo> list = this.boardService.retrieveList();

        model.addAttribute("boardList", list);
        model.addAttribute("content","views/board/boardList");

        return "/templates";
    }

    @GetMapping("/member/detailBoard/{boardNo}")
    public String boardDetail(@PathVariable int boardNo, Model model, @AuthenticationPrincipal UserAccount user) {
        String UserId = user.getUser().getUserId();
        String userNick = user.getUser().getUserNick();
        String userRole = user.getUser().getRole();

        UserVo currnetUser = new UserVo();
        currnetUser.setUserId(UserId);
        currnetUser.setUserNick(userNick);
        currnetUser.setRole(userRole);

        this.boardService.updateUphit(boardNo);
        BoardVo board = boardService.retrieveDetail(boardNo);
        List<CommentVo> comlist = this.boardService.retrieveComList(boardNo);

        int recomCount = this.boardService.ReComCount(boardNo);
        int comCount = this.boardService.countCommemt(boardNo);

        board.setRecomCount(recomCount);
        board.setCommentCount(comCount);


        model.addAttribute("user",currnetUser);
        model.addAttribute("board", board);
        model.addAttribute("commentList", comlist);
        model.addAttribute("content","views/board/detailBoard");


        return "/templates";
    }

    @PostMapping("/deleteBoard/{boardNo}")
    public String boardDelete(@PathVariable int boardNo) {

        this.boardService.remove(boardNo);

        return "redirect:/boardList";
    }

    @GetMapping("/modifyBoardForm/{boardNo}")
    public String boardModifyForm(@PathVariable int boardNo, Model model, @AuthenticationPrincipal User principal) {

        BoardVo board = boardService.retrieveDetail(boardNo);
        model.addAttribute("board", board);
        return "views/board/modifyBoard";
    }

    @PostMapping("/modifyboard")
    public String boardModify(BoardVo board) {
        int boardNo = board.getBoardNo();

        this.boardService.modifyBoard(board);
        return "redirect:/detailBoard/" + boardNo;
    }

    @GetMapping("/writeboardForm")
    public String boardWriteForm(Model model) {

        model.addAttribute("content","views/board/boardWrite");


        return "/templates";
    }

    @PostMapping("/boardWrite")
    public String boardWrtie(@ModelAttribute("board") @Valid BoardVo board,
                             BindingResult bindingResult, Model model,
                             RedirectAttributes attributes, @AuthenticationPrincipal User principal) throws Exception {
        board.setUserId(principal.getUsername());

        System.out.println("!board.getFileList() " + board.getFileList());

        if (!board.getFileList().isEmpty()) {

            List<BoardFileVo> boardFile = FileUtils.uploadFiles(board.getFileList());

            board.setBoardFiles(boardFile);

        }

        this.boardService.registerBoard(board);

        return "redirect:/boardList";
    }

    //신고 게시글 리스트 조회
    @GetMapping("/admin/reportList")
    public String reportList(@RequestParam String userId, Model model) {
        List<ReportVo> users = this.boardService.retrieveReportList(userId);
        model.addAttribute("users", users);
        model.addAttribute("content","views/board/reportList");
        return "/templates";
    }

    // 사용자 정보 조회 ajax
    @GetMapping("/admin/getReportList/{userId}")
    public @ResponseBody
    List<ReportVo> reportList(@PathVariable String userId) {
        List<ReportVo> users = this.boardService.retrieveReportList(userId);
        System.out.println(users);
        return users;
    }

    @GetMapping("/member/myBoardList")
    public String myBoardList(@AuthenticationPrincipal User prin, Model model) {
        String userId = prin.getUsername();
        List<BoardVo> boards = this.boardService.retrieveUserBoardList(userId);
        model.addAttribute("boards", boards);
        model.addAttribute("content","views/board/userBoardList");
        return "/templates";
    }

    @GetMapping("/member/listUserBoard")
    public @ResponseBody List<BoardVo> listUserBoard(@AuthenticationPrincipal User prin) {
        String userId = prin.getUsername();
        List<BoardVo> boards = this.boardService.retrieveUserBoardList(userId);
        return boards;
    }

    //선택게시글 삭제
    @RequestMapping(value = "/member/userDeleteList", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> userDeleteBoard(@RequestBody String[] valueArr, @AuthenticationPrincipal User prin,
                                                             Model model){
        int size = valueArr.length;
        for(int i = 0; i < size; i++) {
            int no = Integer.parseInt(valueArr[i]);
            this.boardService.remove(no);
        }
        Map<String, Object> map =  new HashMap<String, Object>();
        String userId = prin.getUsername();
        List<BoardVo> boards = this.boardService.retrieveUserBoardList(userId);
        map.put("boards", boards);
        return map;
    }


    @GetMapping("/hihi")
    public String hihihi(){
        return "views/board/아아";
    }


}

