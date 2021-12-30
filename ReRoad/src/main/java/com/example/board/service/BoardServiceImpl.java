package com.example.board.service;

import com.example.board.dao.BoardDao;
import com.example.board.vo.BoardVo;
import com.example.common.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDao boardDao;

    @Override
    public List<BoardVo> retrieveList() {
        return this.boardDao.readAll();
    }

    @Override
    public List retrieveSearchWhole(String keyword) {
        return this.boardDao.selectSearchWhole(keyword);
    }

    @Override
    public List retrieveSearchTitle(String keyword) {
        List<BoardVo> list = this.boardDao.selectSearchTitle(keyword);
        return list;
    }

    @Override
    public List retrieveSearchContent(String keyword) {
       return  this.boardDao.selectSearchContent(keyword);
    }

    @Override
    public List retrieveSearchUserNick(String keyword) {
        return this.boardDao.selectSearchUserNick(keyword);
    }
}
