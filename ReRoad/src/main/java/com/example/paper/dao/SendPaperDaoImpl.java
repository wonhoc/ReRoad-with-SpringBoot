package com.example.paper.dao;

import com.example.paper.vo.SendPaperVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

@Repository("sendPaperDao")
public class SendPaperDaoImpl implements SendPaperDao {

    @Autowired
    private SqlSession sqlSession;

    // senderPaper에 insert하고 pk값 반환받기
    @Override
    public int insertSendPaper(SendPaperVo sendVo) {
        HashMap<String, Object> sendPaper = new HashMap<String, Object>();
        sendPaper.put("senderNick", sendVo.getSenderNick());
        sendPaper.put("sendPaperContent", sendVo.getPaperContent());

        //DB 에 insert하기
        this.sqlSession.insert("Paper.insertSendPaper", sendPaper);

        // Mapper에서 usegenerateKey=true 를 통해 Insert 하면서 값을 반환 받음
        // 반환 받을 변수명을 keyProperty로 지정
        // 반환 받을 값으로 sendPaper의 PK를 지정
        BigInteger tempPaperNo = (BigInteger)sendPaper.get("sendPaperNo");
        int sendPaperNo = tempPaperNo.intValue();
        return sendPaperNo;
    }

    @Override
    public List<SendPaperVo> selectSendPaperList(String userNick) {
        return this.sqlSession.selectList("Paper.selectSendPaperList", userNick);
    }
}
