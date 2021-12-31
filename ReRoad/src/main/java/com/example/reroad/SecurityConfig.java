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

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Spring Security Login - For Binding UserDetail Interface
    @Autowired
    private UserService userService;
    //페이지 권한 관리
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.csrf();
//        CookieCsrfTokenRepository cookieCsrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
//        cookieCsrfTokenRepository.setCookieName("XSRF-TOKEN");
//        http.csrf().csrfTokenRepository(cookieCsrfTokenRepository);

        http.csrf().disable();

        log.info("security config...");
        http.csrf().disable();
        //Root 페이지 : 모든 권한 접속 가능
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        http.authorizeHttpRequests().antMatchers("/member/**").authenticated();
        //http.authorizeHttpRequests().antMatchers("/manager/**").hasRole("MANAGER");
        //ADMIN 권한 테스트
        http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
        //로그인
        http.formLogin().loginPage("/loginForm").defaultSuccessUrl("/loginSuccess", true);

        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.logout().logoutUrl("/loginSuccess").invalidateHttpSession(true).logoutSuccessUrl("/loginForm");

        http.userDetailsService((UserDetailsService) userService);

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}