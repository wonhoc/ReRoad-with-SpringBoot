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

    // 로그인 후 아이디로 회원 정보 반환(ID, NickName, Role)
    @Override
    public UserVo getUserInfo(String username) {
        return this.sqlSession.selectOne("Member.getInfoFromDB", username);
    };
    
    // 회원 가입
    @Override
    public void insertUser(Map map) { this.sqlSession.selectOne("Member.inputUser", map);}
    ;

}