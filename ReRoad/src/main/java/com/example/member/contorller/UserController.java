package com.example.member.contorller;

import com.example.member.service.MailService;
import com.example.member.service.UserService;
import com.example.member.vo.MailVo;
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
import java.util.Random;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public HttpSession session;

    @Autowired
    public MailService mailService;

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

    // 회원 가입 과정에서 아이디 중복 체크
    @RequestMapping(value="/checkId", method = RequestMethod.POST)
    public @ResponseBody String checkIdAjax(@RequestParam("userId") String requestId) {
        String inputId = requestId.trim();
        int checkNum = this.userService.checkId(requestId);
        String checkResult = "";
        // 중복된 아이디는 1 = 가입 불가
        // 없는 아이디는 0 = 가입 가능
        if (checkNum == 1) {
            checkResult = "false";
        } else if (checkNum == 0) {
            checkResult = "true";
        }
        return checkResult;
    }

    //회원가입
    @RequestMapping(value="/joinUserRequest", method = RequestMethod.POST)
    public @ResponseBody void joinUserRequest() {

    }

    //인증 메일 발송
    @PostMapping("/verifyEmail")
    public @ResponseBody String sendEmail(@RequestParam("mail") String email) {
        String key="";
        Random random = new Random();
        for(int i = 0; i<3; i++) {
            int index = random.nextInt(25)+65;
            key += (char)index;
        }
        int numIndex = random.nextInt(9999)+1000;
        key += numIndex;
        MailVo mail = new MailVo();
        mail.setAddress(email);
        mail.setTitle("ReRoad 회원 가입을 위한 인증 메일입니다.");
        mail.setMessage("인증 번호는 "+ key + "입니다.");

        this.mailService.sendMail(mail);
        System.out.println("Controller Key : "+key);

        return key;

    }


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