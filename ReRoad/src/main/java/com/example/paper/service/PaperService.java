package com.example.paper.service;

import com.example.paper.vo.ReceivePaperVo;
import com.example.paper.vo.SendPaperVo;

import java.util.ArrayList;

public interface PaperService {

    void registerPaper (SendPaperVo sendVo, ArrayList<ReceivePaperVo> receivePaper);

}
