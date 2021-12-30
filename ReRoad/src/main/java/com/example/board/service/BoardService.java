package com.example.board.service;

import com.example.board.vo.BoardVo;


import java.util.List;


public interface BoardService {

    List<BoardVo> retrieveList();

    List retrieveSearchWhole(String keyword);
    List retrieveSearchTitle(String keyword);
    List retrieveSearchContent(String keyword);
    List retrieveSearchUserNick(String keyword);

}
