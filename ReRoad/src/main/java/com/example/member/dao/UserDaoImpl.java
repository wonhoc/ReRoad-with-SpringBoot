package com.example.member.dao;

import com.example.member.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
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
    }

    ;

    // 회원 가입
    @Override

    public void insertUser(Map map) {
        this.sqlSession.selectOne("Member.inputUser", map);
    }

    ;


    //아이디 중복 체크
    @Override
    public int existId(String userId) {
        return this.sqlSession.selectOne("Member.checkId", userId);

    }


    //회원정보조회
    @Override
    public List<UserVo> selectUserList() {
        return this.sqlSession.selectList("Member.selectAll");
    }

    //회원강제탈퇴시 회원 삭제
    @Override
    public void deleteUserForce(String userId) {
        this.sqlSession.delete("Member.deleteUserForce", userId);
    }

    //비밀번호 일치확인
    @Override
    public String confirmPwd(String userId) {
        return this.sqlSession.selectOne("Member.selectPwd", userId);
    }

    //회원자진탈퇴시 회원 삭제
    @Override
    public void deleteUser(String userId) {
        this.sqlSession.delete("Member.deleteUser", userId);

    }
}