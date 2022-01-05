package com.example.member.oauth;

import com.example.member.dao.UserDao;
import com.example.member.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

     @Override
        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest: "+userRequest.getClientRegistration());
        System.out.println("AccessToken: "+userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: "+super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = oAuth2User.getAttribute("email");
        String password = this.passwordEncoder.encode("asdfasdf!234");
        String role = ("ROLE_MEMBER");

        UserVo user = new UserVo();
        int checkUser = userDao.existId(username);
        if(checkUser == 0) {
            System.out.println("구글 로그인이 최초입니다.");
            user.setUserId(username);
            user.setUserPwd(password);
            user.setUserNick(String.format("Google_$s",username));
            user.setRole(role);
            user.setProvider(provider);
            user.setProviderId(providerId);
            this.userDao.insertUser(user);
        } else {
        System.out.println("이미 로그인 기록이 있습니다. 자동 회원 가입이 되어 있습니다.");
        }

      return super.loadUser(userRequest);
    }
}
