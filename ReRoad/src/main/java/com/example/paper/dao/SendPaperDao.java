package com.example.paper.dao;

import com.example.paper.vo.SendPaperVo;

import java.util.List;

public interface SendPaperDao {
    int insertSendPaper(SendPaperVo sendVo);
    List<SendPaperVo> selectSendPaperList(String userNick);
    String getReceiverId(String receiveNick);
    SendPaperVo selectSendPaper(int sendPaperNo);
}
