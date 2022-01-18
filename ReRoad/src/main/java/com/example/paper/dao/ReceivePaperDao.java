package com.example.paper.dao;

import com.example.paper.vo.ReceivePaperVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ReceivePaperDao {
    void insertPaper(ArrayList<ReceivePaperVo> receiveVo, int sendPaperNo);
    List<ReceivePaperVo> selectReceivePaperList(String receiveId);
    ReceivePaperVo selectReceivePaper(HashMap<String, Object> receivePaperMap);
    void updateReceiveRead(HashMap<String,Object> updateMap);
    void deleteReceivePaper(HashMap<String,Object> map);
}
