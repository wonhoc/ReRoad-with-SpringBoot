package com.example.member.contorller;

import com.example.member.service.UserService;
import com.example.member.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller()
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    public HttpSession session;

    //로그인 폼
    @GetMapping("/loginForm")
    public String login() { return "views/member/loginForm"; }

    // 로그인 성공시 이동 페이지(임시)
    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal User prin, Model model) {
        model.addAttribute("username", prin.getUsername());
        model.addAttribute("userRole", prin.getAuthorities());

        // 로그인 후 세션에 UserVo 객체 등록
        String userId = prin.getUsername();
        UserVo user = this.userService.getInfo(userId);
        String userNick = user.getUserNick();
        String userRole = user.getRole();

        session.setAttribute("loginUser", user);
        return "views/member/loginSuccess";
    }

    @GetMapping("/admin")
    public String forAdmin() { return "views/member/admin"; }
}