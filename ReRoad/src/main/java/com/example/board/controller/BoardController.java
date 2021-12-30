package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardFileVO;
import com.example.board.vo.BoardVO;
import com.example.file.FileUploadService;
import com.example.member.service.UserService;
import com.example.member.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.Collection;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private UserService userService;

    @GetMapping("register")
    public String form(){
        return "views/board/boardWrite";
    }


    //게시글 리스트
    @GetMapping("/boardListform")
    public String boardListform(Model model){
        return "views/board/boardlist";
    }




    @PostMapping("/fileUpload")
    public String insertBoard(BoardVO board, @RequestPart(value = "boardFileInput", required = false) MultipartFile boardFileSys,
                           Model model, HttpSession session, HttpServletRequest request){
        BoardFileVO boardfile = new BoardFileVO();
        UserVo userInfo = (UserVo) session.getAttribute("userInfo");


try{

        Collection<Part> parts = request.getParts();

        String userId = "admin123";


        String userId1 = board.getUserId();


        String boardFileName = null;
    for (Part part : parts) {
        String name = part.getName();

        if(!part.getHeader("Content-Disposition").contains("boardFile")){

            if (name.equals("boardTitle")){
                board.setBoardTitle(request.getParameter(name));
            }else if(name.equals("boardContent")){
                board.setBoardContent(request.getParameter(name));
            }
        }else{
            if(!boardFileSys.getOriginalFilename().isEmpty()){
                boardfile.setBoardfileOrigin(boardFileSys.getOriginalFilename());
                System.out.println("getOriginalFilename : "+ boardFileSys.getOriginalFilename());

                boardFileName = fileUploadService.boardRestore(boardFileSys, request);
                System.out.println("boardFileName : "+ boardFileName);
                boardfile.setBoardfileSys(boardFileName);

                System.out.println("getSize : "+ boardFileSys.getSize());
                boardfile.setBoardfileSize(boardFileSys.getSize());




                int no = this.boardService.lastId();
                boardfile.setBoardNo(no);
                this.boardService.createFile(boardfile);
            }
        }
    }

        board.setUserId(userId);

        this.boardService.createBoard(board);


}catch (Exception e){
        e.printStackTrace();
}
        return "redirect:boardlist";
    }

}
