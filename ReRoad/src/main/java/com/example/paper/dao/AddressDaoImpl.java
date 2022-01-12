package com.example.paper.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("addressDao")
public class AddressDaoImpl implements AddressDao {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertAddr(Map map) {
        this.sqlSession.insert("Paper.insertAddress", map);
    }


}
