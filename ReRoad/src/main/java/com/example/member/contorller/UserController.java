package com.example.member.contorller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class UserController {
    //로그인 폼
    @GetMapping("/loginForm")
    public String login() { return "views/member/loginForm"; }

    // 로그인 성공시 이동 페이지(임시)
    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal User prin, Model model) {
        model.addAttribute("username", prin.getUsername());
        model.addAttribute("userRole", prin.getAuthorities());
        return "views/member/loginSuccess";

    }

    @GetMapping("/admin")
    public String forAdmin() { return "views/member/admin"; }
}