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

import javax.servlet.http.HttpSession;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    public HttpSession session;

     @Override
        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest: "+userRequest.getClientRegistration());
        System.out.println("AccessToken: "+userRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: "+super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);

//        String provider = userRequest.getClientRegistration().getClientId();
//        String providerId = oAuth2User.getAttribute("sub");
         //OAuth2User에 있는 Email을 아이디로 지정하고 ROLE를 MEMBER로 지정한다..
        String username = oAuth2User.getAttribute("email");
        String role = ("ROLE_MEMBER");

        //DB상에 중복된 아이디가 있는지 검사 해서 있다면 1, 없다면 0을 반환한다.
        int checkUser = userDao.existId(username);

        if(checkUser == 0) {
            //Attribute에서 가져온 정보를 Vo 객체에 담고 Dao의 회원가입 메소드를 호출
            System.out.println("구글 로그인이 최초입니다.");
            UserVo user = new UserVo();
            user.setUserId(username); 
            user.setUserNick(String.format("Google_%s",username)); 
            user.setRole(role);
            session.setAttribute("loginUser", user);
            this.userDao.insertUser(user);
        } else {
            //회원 아이디를 통해 DB에서 정보를 가져온 후 Session에 등록한다.
            UserVo user = this.userDao.getUserInfo(username);
            session.setAttribute("loginUser", user);
            System.out.println("이미 로그인 기록이 있습니다. 자동 회원 가입이 되어 있습니다.");
        }
      return super.loadUser(userRequest);
    }
}
