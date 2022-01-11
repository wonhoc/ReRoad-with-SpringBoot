package com.example.paper.controller;

import com.example.member.vo.UserAccount;
import com.example.paper.vo.AddressVo;
import com.example.paper.vo.ReceivePaperVo;
import com.example.paper.vo.SendPaperVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PaperController {

    //헤더 버튼 클릭 시 쪽지 메인으로 이동
    @GetMapping("/defaultPaper")
    public String goPaper() {
        return "/views/paper/sendPaper";
    }

    // 쪽지 전송
    @PostMapping("/sendPaper")
    public @ResponseBody void sendPaper(@AuthenticationPrincipal UserAccount user,
                                          @RequestParam("receiveNick") String receiveNick,
                                          @RequestParam("sendContent") String content) {
        //보낼 사람 닉네임
        String userNick = user.getUser().getUserNick();
        // 받을 사람 String Array로 나눠서 받기
        String[] receiverArray = receiveNick.split(",");

        // 보낸 쪽지함 관련 생성

        // insert 할 객체 생성
        SendPaperVo sendPaper = new SendPaperVo();
        // 보낸 쪽지함 - 닉네임
        sendPaper.setSenderNick(user.getUser().getUserNick());
        // 보낸 쪽지함 - 쪽지 내용
        sendPaper.setSendPaperContent(content);

        // 받은 쪽지함 관련
        //받을 사람 닉네임 배열 생성
        ArrayList<AddressVo> addrs = new ArrayList<AddressVo>();
         //받은 쪽지함 관련 - VO 생성
        ArrayList<ReceivePaperVo> receivePapers = new ArrayList<ReceivePaperVo>();
        // 반복문으로 입력
        for(String nick: receiverArray) {
            AddressVo addr = new AddressVo(); //addr에 넣을 객체 새롭게 생성
            addr.setReceivedNicks(nick); // addr에 닉네임 넣기
            addrs.add(addr); // addrs에 addr

            ReceivePaperVo receivePaper = new ReceivePaperVo();
            receivePaper.setWriter(userNick); // 보낼 사람 입력
            receivePaper.setReceivePaperContent(content);

            receivePaper.setReceiveNick(nick);
            receivePapers.add(receivePaper);
        }
        sendPaper.setReceiverNicks(addrs);

    }
}
