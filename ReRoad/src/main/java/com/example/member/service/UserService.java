package com.example.member.service;

import com.example.member.vo.UserVo;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface UserService {

    void registUser(UserVo user);
    UserVo getInfo(String username);
    int checkId(String isId);
    int checkNick(String isNick);
    void updateTempPwd(UserVo user);
    Map <String, String> validate(BindingResult bindingResult);

    List<UserVo> retrieveUserList();
    void removeUserForce(String userId);
    String checkPwd(String userId);
    void removeUser(String userId);

    UserVo retrieveUser(String userId);
    void modifyUser(UserVo user);
}