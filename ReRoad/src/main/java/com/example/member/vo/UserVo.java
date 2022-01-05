package com.example.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    @Email
    private String userId;
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "(?=.*[a-zA-z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{10,30}",
            message = "비밀번호는 영문대소문자, 특수문자, 숫자를 포함해야 합니다")
    private String userPwd;
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @Size(min=5, max = 15, message = "닉네임은 5~15자 사이로 입력해야 합니다.")
    private String userNick;
    private String role;
    private String photoOrigin;
    private String photoSys;
    private MultipartFile photo;
    private String joinDate;
    private String exitType;

    private String provider;
    private String providerId;




}