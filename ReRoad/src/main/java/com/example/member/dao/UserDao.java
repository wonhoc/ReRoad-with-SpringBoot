package com.example.member.dao;

import com.example.member.vo.UserVo;

import java.util.Map;

public interface UserDao {
    UserVo getUserByID(String username);
    void insertUser(Map map);
    void deleteUser(String userId);
    UserVo getUserInfo(String username);
}