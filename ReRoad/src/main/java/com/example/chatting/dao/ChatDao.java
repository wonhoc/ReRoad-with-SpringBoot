package com.example.chatting.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDao {

    @Autowired
    private SqlSession sqlSession;

    public String findNick(String userId){

        return this.sqlSession.selectOne("findNick", userId);
    }
}
