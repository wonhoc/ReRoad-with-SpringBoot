package com.example.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile photo;
    private String joinDate;
    private String exitType;




}