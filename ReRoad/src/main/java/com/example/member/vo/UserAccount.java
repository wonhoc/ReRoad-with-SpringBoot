package com.example.member.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter

public class UserAccount extends User {

    private UserVo user;
    // UserVo + 권한 담은 객체
    // UserAccount(UserVo, 권한Role)
    public UserAccount(UserVo user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUserId(),user.getUserPwd(), authorities);
        this.user = user;
    }
}
