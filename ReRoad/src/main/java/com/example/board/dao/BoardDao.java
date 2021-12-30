package com.example.board.dao;

import com.example.board.vo.BoardVo;
import com.example.common.SearchVO;

import java.util.List;
import java.util.Map;

public interface BoardDao {

    List<BoardVo> readAll();

    List selectSearchWhole(String keyword);
    List selectSearchTitle(String keyword);
    List selectSearchUserNick(String keyword);
    List selectSearchContent(String keyword);

}
