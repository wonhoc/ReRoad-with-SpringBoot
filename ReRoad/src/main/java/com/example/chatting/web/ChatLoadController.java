package com.example.chatting.web;

import com.example.member.vo.UserAccount;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatLoadController {
    //페이지로 이동
    @GetMapping("/chatting")
    public String chatLoad(Model model, @AuthenticationPrincipal UserAccount user) {
        String username = user.getUser().getUserNick();
        model.addAttribute("username", username);
        return "views/chatting/chatUserList.html";
    }

    //페이지로 이동
    @GetMapping("/join/with")
    public String chatLoad(Model model, String username) {

        model.addAttribute("othername", username);
        return "views/chatting/index.html";
    }
}
