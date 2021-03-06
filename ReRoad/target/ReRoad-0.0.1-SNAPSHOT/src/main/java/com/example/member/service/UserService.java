package com.example.member.service;

import com.example.member.vo.UserVo;

import java.util.List;
import java.util.Map;

public interface UserService {
    void registUser(Map map, String userId);
    UserVo getInfo(String username);
    int checkId(String isId);
    int checkNick(String isNick);

    List<UserVo> retrieveUserList();
    void removeUserForce(String userId);
    String checkPwd(String userId);
    void removeUser(String userId);
}