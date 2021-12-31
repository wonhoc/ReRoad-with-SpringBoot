package com.example.board.controller;

import com.example.board.service.BoardService;

import com.example.board.vo.BoardVo;
import com.example.common.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;

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

        map.put("results",list);

        return map;
    }

}
