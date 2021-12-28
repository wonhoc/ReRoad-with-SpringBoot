package com.example.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String userId;
    private String userPwd;
    private String userNick;
    private String role;
    private String photoOrigin;
    private String photoSys;
    private String joinDate;
    private String exitTytpe;


}