package com.example.paper.dao;

import com.example.paper.vo.ReceivePaperVo;

import java.util.ArrayList;

public interface ReceivePaperDao {
    void insertPaper(ArrayList<ReceivePaperVo> receiveVo, int sendPaperNo);
}
