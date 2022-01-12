package com.example.paper.service;

import com.example.paper.dao.AddressDao;
import com.example.paper.dao.ReceivePaperDao;
import com.example.paper.dao.SendPaperDao;
import com.example.paper.vo.AddressVo;
import com.example.paper.vo.ReceivePaperVo;
import com.example.paper.vo.SendPaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service("paperService")
public class PaperServiceImpl implements PaperService{

    @Autowired
    private ReceivePaperDao receivePaperDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private SendPaperDao sendPaperDao;

    //쪽지 작성
    @Override
    public void registerPaper (SendPaperVo sendVo, ArrayList<ReceivePaperVo> receivePaper) {
        //DB에 저장하고 해당 메시지의 PK값 받아오기
        int sendPaperNo = this.sendPaperDao.insertSendPaper(sendVo);

        ArrayList<AddressVo> addresses = sendVo.getReceiveInfo();

        HashMap<String, Object> addressMap = new HashMap<String, Object>();
        addressMap.put("sendPaperNo", sendPaperNo);

        for(AddressVo tempAddr : addresses) {
            addressMap.put("receiveNick", tempAddr.getReceiveNick());
            // Address에 저장
            this.addressDao.insertAddr(addressMap);
        }
        // ReceivePaper에 저장
        this.receivePaperDao.insertPaper(receivePaper,sendPaperNo);

    }
}
