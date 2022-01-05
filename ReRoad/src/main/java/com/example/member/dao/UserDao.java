package com.example.member.dao;

import com.example.member.vo.UserVo;

import java.util.List;
import java.util.Map;

public interface UserDao {
    UserVo getUserByID(String username);
    void insertUser(UserVo user);
    UserVo getUserInfo(String username);
    int existId(String userId);
    int existNick(String userNick);
    List<UserVo> selectUserList();
    void deleteUserForce(String userId);
    String confirmPwd(String userId);
    void deleteUser(String userId);
    UserVo selectUser(String userId);
    void update(UserVo user);
}
