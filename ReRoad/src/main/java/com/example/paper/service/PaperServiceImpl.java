package com.example.paper.service;

import com.example.paper.dao.AddressDao;
import com.example.paper.dao.ReceivePaperDao;
import com.example.paper.dao.SendPaperDao;
import com.example.paper.vo.AddressVo;
import com.example.paper.vo.ReceivePaperVo;
import com.example.paper.vo.SendPaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            addressMap.put("receiveUserId", tempAddr.getReceiveId());
            // Address에 저장
            this.addressDao.insertAddr(addressMap);
        }
        // ReceivePaper에 저장
        this.receivePaperDao.insertPaper(receivePaper,sendPaperNo);

    }
    // 보낸 쪽지 리스트 가져오기
    @Override
    public List<SendPaperVo> retrieveSendPaperList(String userId) {
        return this.sendPaperDao.selectSendPaperList(userId);
    }

    // 받는 회원의 닉네임으로 아이디 가져오기
    @Override
    public String retrieveReceiveId(String receiveNick) {
        return this.sendPaperDao.getReceiverId(receiveNick);
    }

    //보낸 메시지 상세 보기
    @Override
    public SendPaperVo retrieveSendPaper(int sendPaperNo) {
        return this.sendPaperDao.selectSendPaper(sendPaperNo);
    }

    // 보낸 메시지 삭제
    @Override
    public void removeSendPaper(int[] sendPaperNoS) { this.sendPaperDao.deleteSendPaper(sendPaperNoS);};

    // 받는 쪽지 리스트 가져오기
    @Override
    public List<ReceivePaperVo> retrieveReceivePaperList(String receiveId) {
        return this.receivePaperDao.selectReceivePaperList(receiveId);
    }

    // 받는 쪽지 상세 보기
    @Override
    public ReceivePaperVo retrieveReceivePaper(HashMap<String,Object> map) {
        return this.receivePaperDao.selectReceivePaper(map);
    }
    //읽음 상태 업데이트
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRead(int receivePaperNo, String receiveId) throws TransactionException  {
        //map
        HashMap<String,Object> updateMap = new HashMap<String,Object>();

        //업데이트 할 정보
        updateMap.put("receivePaperNo", receivePaperNo);
        updateMap.put("receiveId",receiveId);

        //쪽지 수신과 연관된 테이블로 읽음 상태 전달
        this.receivePaperDao.updateReceiveRead(updateMap);
        this.addressDao.updateAddressRead(updateMap);

    }

    //받는 메시지 삭제
    @Override
    public void removeReceivePaper(HashMap<String,Object> map) {
        this.receivePaperDao.deleteReceivePaper(map);
    }

}
