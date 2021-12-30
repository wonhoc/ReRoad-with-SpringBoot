package com.example.board.controller;

import com.example.board.service.BoardService;
import com.example.common.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/search")
    public String searchBoardLi(){

        return "hello";
    }
}
