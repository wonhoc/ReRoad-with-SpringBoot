package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardVO;
import com.example.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;


    @GetMapping("/boardList")
    public Map boardList(Model model){
        HashMap map = new HashMap<String, Object>();
        this.boardService.retrieveBoardList(map);
        List<BoardVO> list = (List<BoardVO>)map.get("results");
        map.put("code", "success");
        return map;
    }

}
