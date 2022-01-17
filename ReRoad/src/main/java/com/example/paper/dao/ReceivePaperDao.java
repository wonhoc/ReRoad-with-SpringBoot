package com.example.paper.dao;

import com.example.paper.vo.ReceivePaperVo;

import java.util.ArrayList;
import java.util.List;

public interface ReceivePaperDao {
    void insertPaper(ArrayList<ReceivePaperVo> receiveVo, int sendPaperNo);
    List<ReceivePaperVo> selectReceivePaperList(String receiveId);
}
