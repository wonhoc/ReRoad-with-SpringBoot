package com.example.member.dao;

import com.example.member.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SqlSession sqlSession;

    // 로그인 시 Mapper로 회원 아이디 전송.
    @Override
    public UserVo getUserByID(String username) {
        return this.sqlSession.selectOne("Member.getUserById", username);
    }
    // 회원 가입
    @Override
    public void insertUser(Map map) {
        this.sqlSession.selectOne("Member.insertUser", map);
    }
    // 회원 탈퇴[Test]
    @Override
    public void deleteUser(String userId) {
        this.sqlSession.delete("Member.testDelete", userId);
    }

}