package com.example.board.controller;

import com.example.board.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/search")
    public String searchBoardLi(){

        return "hello";
    }
}
