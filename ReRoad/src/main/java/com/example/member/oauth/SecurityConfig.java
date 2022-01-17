package com.example.member.oauth;

import com.example.member.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Spring Security Login - For Binding UserDetail Interface
    @Autowired
    private UserService userService;

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    //페이지 권한 관리
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable(); // 토큰 비활성화

        //권한 설정
        http.authorizeRequests()
                .antMatchers("/","/css/images/**","/js/**","/css/**","/templates/**").permitAll() //모든 사용자 권한으로 접근 가능
                .antMatchers("/member/**").authenticated() //회원 권한의 사용자일 경우 접속 가능한 경로
                .antMatchers("/admin/**").hasRole("ADMIN") //ADMIN 권한의 사용자일 경우 접속 가능한 경로
                .antMatchers("/JoinUser").anonymous();//회원가입은 로그인 안한 사용자만 접근 가능


        //로그인 관련 설정
        http.formLogin()
                .loginPage("/loginForm") // 로그인 폼 경로
                .successForwardUrl("/loginOk") // 로그인 성공 시 이동할 URL
                .failureForwardUrl("/loginFail"); // 로그인 실패 시 이동 URL


        //로그아웃
        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");
        //OAuth2 인증
        http.oauth2Login()
                .loginPage("/loginForm")
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);

        http.userDetailsService((UserDetailsService) userService);

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/","/images/**","/css/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}