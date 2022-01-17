package com.example.chatting.web;

import com.example.member.vo.UserAccount;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ChatLoadController {
    //페이지로 이동
    @GetMapping("/member/chatting")
    public String chatLoad(Model model, @AuthenticationPrincipal UserAccount user) {

        model.addAttribute("myname", user.getUser().getUserNick());
        return "views/chatting/index.html";
    }

  /*  //페이지로 이동
    @GetMapping("/join/with")
    public String chatLoad(Model model, String username, @AuthenticationPrincipal UserAccount user) {

        model.addAttribute("myname", user.getUser().getUserNick());
        model.addAttribute("othername", username);
        return "views/chatting/index.html";
    }*/
}
