package com.example.member.service;

import com.example.member.attribute.OAuthAttributes;
import com.example.member.dao.UserDao;
import com.example.member.vo.UserVo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserDao userDao;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {


        OAuth2UserService delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        // OAuth 로그인을 사용할 서비스명을 문자열로 받음
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth 로그인 시 key 값
        String userNameAttribute = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        // OAuth 로그인을 통해 가져온 유저 정보를 Attribute에 담음
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttribute, oAuth2User.getAttributes());

        Set<GrantedAuthority> authorities = new LinkedHashSet<>();

        authorities.add(new OAuth2UserAuthority(attributes.getAttributes()));
        OAuth2AccessToken token = userRequest.getAccessToken();

        for (String authority : token.getScopes()) {
            authorities.add(new SimpleGrantedAuthority("SCOPE_"+authority));
        }
        return new DefaultOAuth2User(authorities, attributes.getAttributes(), attributes.getNameAttributeKey());
    }
}
