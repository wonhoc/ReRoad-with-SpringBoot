package com.example.member.contorller;

import com.example.member.service.UserService;
import com.example.member.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @GetMapping("/joinUser")
    public String forJoin() { return "views/member/joinuser";}

    // 관리자의 사용자 정보 조회
    @GetMapping("/admin/listUser")
    public String listUser(Model model) {
        List<UserVo> users = this.userService.retrieveUserList();
        model.addAttribute("userList", users);
        return "views/member/listUser";
    }


    // 사용자 정보 조회 ajax
    @GetMapping("/admin/getList")
    public @ResponseBody List<UserVo> userList() {
        List<UserVo> users = this.userService.retrieveUserList();
        return users;
    }


    // 선택회원 강제 탈퇴
    @RequestMapping(value = "/admin/deleteUserList", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> userListDelete(@RequestBody String[] valueArr, Model model) {
        int size = valueArr.length;
        for (int i = 0; i<size; i++){
            this.userService.removeUserForce(valueArr[i]);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserVo> users = this.userService.retrieveUserList();
        map.put("userList", users);
        return map;
    }

    //비밀번호 일치 확인 후 삭제 페이지 이동
    @PostMapping("/pwdCheck")
    public String pwdCheck(Authentication authentication, @RequestParam String userPwd) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String userId = userDetails.getUsername();

        String checkPwd = this.userService.checkPwd(userId);

        boolean result = passwordEncoder.matches(userPwd, checkPwd);

        if (result) {
            return "views/member/deleteUser";
        } else {
            return "views/member/pwdCheckFail";
        }
    }

    //비밀번호 일치하지 않을 시 경고창 띄워주기
    @GetMapping("/pwdCheckFail")
    public String loginFailOne() {
        return "views/member/pwdCheckFail";
    }

    @GetMapping("/deleteUser")
    public String deleteUser() {
        return "views/member/deleteUser";
    }

    @GetMapping("/exitUser")
    public String exitUser() {
        return "views/member/pwdCheck";
    }


    //회원 자진 탈퇴 시 회원정보 삭제
    @PostMapping("/deleteUser")
    public String deleteUser(Authentication authentication, HttpSession session){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String userId = userDetails.getUsername();

        this.userService.removeUser(userId);

        session.invalidate();

        System.out.println(userId);

        return "redirect:/main";
    }
}