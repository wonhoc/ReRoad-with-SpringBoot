package com.example.domestic.dao;

import com.example.domestic.vo.DomesticVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("domesticDao")
public class DomesticDaoImpl implements DomesticDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List selectDomesticMain() {
        return sqlSession.selectList("Board.domesticMain");
    }

    @Override
    public List selectEntireDomestic() {
        return sqlSession.selectList("Board.domesticAll");
    }

    @Override
    public DomesticVo selectRain(String domesticName) {
        return this.sqlSession.selectOne("Board.domestic", domesticName);
    }

    @Override
    public void updateDomestic(DomesticVo domestic) {
        this.sqlSession.update("Board.updateDomestic",domestic);
    }
}
