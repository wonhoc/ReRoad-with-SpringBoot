package com.example.paper.dao;

import com.example.paper.vo.ReceivePaperVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository("receivePaperDao")
public class ReceivePaperDaoImpl implements ReceivePaperDao{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertPaper(ArrayList<ReceivePaperVo> receiveVo, int sendPaperNo) {
        for(ReceivePaperVo receivePaper: receiveVo) {
            receivePaper.setSendPaperNo(sendPaperNo);
            this.sqlSession.insert("Paper.insertReceivePaper",receivePaper);
        }

    }
    @Override
    public List<ReceivePaperVo> selectReceivePaperList(String receiveId) {
        return this.sqlSession.selectList("Paper.selectReceivePaperList",receiveId);
    }
    @Override
    public ReceivePaperVo selectReceivePaper(HashMap<String, Object> receivePaperMap) {
        return this.sqlSession.selectOne("Paper.selectReceivePaper", receivePaperMap);
    }
    @Override
    public void updateReceiveRead(HashMap<String,Object> updateMap) {

        System.out.println("u" + updateMap.toString());

        this.sqlSession.update("Paper.updateReceiveRead",updateMap);

        System.out.println("end");

    }
    @Override
    public void deleteReceivePaper(HashMap<String,Object> map) {
        this.sqlSession.delete("Paper.deleteReceivePaper",map);
    }

}
