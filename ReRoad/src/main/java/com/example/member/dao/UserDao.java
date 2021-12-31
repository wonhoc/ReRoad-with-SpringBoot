package com.example.member.dao;

import com.example.member.vo.UserVo;

import java.util.List;
import java.util.Map;

public interface UserDao {
    UserVo getUserByID(String username);
    void insertUser(Map map);
    UserVo getUserInfo(String username);
    List<UserVo> selectUserList();
    void deleteUserForce(String userId);
    String confirmPwd(String userId);
    void deleteUser(String userId);
}