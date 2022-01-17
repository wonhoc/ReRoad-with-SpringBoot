package com.example.paper.controller;

import com.example.member.vo.UserAccount;
import com.example.paper.dao.SendPaperDao;
import com.example.paper.service.PaperService;
import com.example.paper.vo.AddressVo;
import com.example.paper.vo.ReceivePaperVo;
import com.example.paper.vo.SendPaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class PaperController {

    @Autowired
    private PaperService paperService;
    
    // 쪽지 보내기 페이지로 이동
    @GetMapping("/writePaper")
    public String goWritePaper(Model model) {
        model.addAttribute("content","/views/paper/writePaper");
        return "/templates";
    }

    // 보낸 쪽지함 이동 시 리스트 수신
    @GetMapping("/sendPaper")
    public String sendPaperList(Model model, @AuthenticationPrincipal UserAccount user) {

        String senderNick = user.getUser().getUserNick();
        ArrayList<SendPaperVo> sendPaperList =
                (ArrayList<SendPaperVo>) this.paperService.retrieveSendPaperList(senderNick);

        model.addAttribute("sendPaperList",sendPaperList);
        model.addAttribute("content","/views/paper/sendPaper");

        return "/templates";

    }


    @GetMapping("/receivePaper")
    public String goReceive(Model model) {
        model.addAttribute("content","/views/paper/receivePaper");
        return "/templates";
    }


    // 작성한 쪽지 내용 저장
    @PostMapping("/sendNewPaper")
    public String sendPaper(@RequestParam("receiveNick") String receiveNick,
                          @RequestParam("sendContent") String sendPaperContent,
                          @AuthenticationPrincipal UserAccount user, Model model) {
        //SenderVo 채우기
        SendPaperVo sendPaperVo = new SendPaperVo();
        // 1.작성자 아이디,닉네임 받는 변수 만들기
        String senderId = user.getUser().getUserId();
        String senderNick = user.getUser().getUserNick();
        // 2. 작성 내용 변수 만들기
        String sendContent = sendPaperContent;


        // 3. receiveInfo  내용 채우기
        // 3-1. send +receiver 에서 쓸 ArrayList 먼저 생성해두기
        ArrayList<AddressVo> addressArray = new ArrayList<AddressVo>();
        ArrayList<ReceivePaperVo> receiverVos = new ArrayList<ReceivePaperVo>();


        // 3-2 받는 사람 "," 단위로 쪼개서 배열로 받기
        String[] receivers = receiveNick.split(",");
        // 3-2 사람 수 만큼 반복해서 AddressVo 객체 만들기
        for(String tempReceiver: receivers) {
            // 받는 사람의 닉네임으로 일치하는 아이디 찾아오기
            String tempReceiverId = this.paperService.retrieveReceiveId(tempReceiver);

            AddressVo address = new AddressVo();
            address.setReceiveId(tempReceiverId);
            address.setReceiveNick(tempReceiver);
            addressArray.add(address);

            //ReceivePaper에 정보 미리 저장
            ReceivePaperVo receivePaperVo = new ReceivePaperVo();
            receivePaperVo.setSenderId(senderId);
            receivePaperVo.setSenderNick(senderNick);
            receivePaperVo.setReceivePaperContent(sendPaperContent);
            receivePaperVo.setReceiveId(tempReceiverId);
            receivePaperVo.setReceiveNick(tempReceiver);
            receiverVos.add(receivePaperVo);
        }
        sendPaperVo.setReceiveInfo(addressArray);
        sendPaperVo.setSenderId(senderId);
        sendPaperVo.setSenderNick(senderNick);
        sendPaperVo.setPaperContent(sendPaperContent);

        //DB에 전송
        this.paperService.registerPaper(sendPaperVo,receiverVos);

        model.addAttribute("content","redirect:/paperPage");

        return "/templates";
    }
    @GetMapping("/sendPaperDetail/{sendPaperNo}")
    public String sendPaperDetail(@PathVariable int sendPaperNo, Model model) {
        SendPaperVo sendPaper = this.paperService.retrieveSendPaper(sendPaperNo);
        model.addAttribute("sendPaper",sendPaper);
        model.addAttribute("content","/views/paper/sendPaperDetail");
        return "/templates";


    }



}
