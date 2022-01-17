package com.example.paper.service;

import com.example.paper.vo.ReceivePaperVo;
import com.example.paper.vo.SendPaperVo;

import java.util.ArrayList;
import java.util.List;

public interface PaperService {

    void registerPaper (SendPaperVo sendVo, ArrayList<ReceivePaperVo> receivePaper);
    List<SendPaperVo> retrieveSendPaperList(String userNick);
    String retrieveReceiveId(String receiveNick);
    SendPaperVo retrieveSendPaper(int sendPaperNo);
    void removeSendPaper(int[] sendPaperNoS);

}
