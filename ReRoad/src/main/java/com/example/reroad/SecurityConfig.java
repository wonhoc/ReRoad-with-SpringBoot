package com.example.reroad;

import com.example.member.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Spring Security Login - For Binding UserDetail Interface
    @Autowired
    private UserService userService;
    //페이지 권한 관리
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        //권한 설정
        http.authorizeHttpRequests()
                .antMatchers("/", "/loginForm","joinUser","/forgetPw").permitAll() //모든 사용자 권한으로 접근 가능
                .antMatchers("/member/**").authenticated() //회원 권한의 사용자일 경우 접속 가능한 경로
                .antMatchers("/admin/**").hasRole("ADMIN"); //ADMIN 권한의 사용자일 경우 접속 가능한 경로

        //로그인 관련 설정
        http.formLogin()
                .loginPage("/loginForm") // 로그인 폼 경로
                .defaultSuccessUrl("/loginSuccess", true) // 로그인 성공 시 이동할 URL
                .failureUrl("/loginFail"); // 로그인 실패 시 이동 URL

        //권한이 없는 경로로 접근했을 경우
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        //로그아웃
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/loginForm");


        http.userDetailsService((UserDetailsService) userService);



    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}