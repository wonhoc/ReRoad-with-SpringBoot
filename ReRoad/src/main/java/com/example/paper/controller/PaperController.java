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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class PaperController {

    @Autowired
    private PaperService paperService;

    @GetMapping("/paperPage")
    public String goPaper() {
        return "/views/paper/sendPaper";
    }


    // 작성한 쪽지 내용 저장
    @PostMapping("/sendPaper")
    public String sendPaper(@RequestParam("receiveNick") String receiveNick,
                          @RequestParam("sendContent") String sendPaperContent,
                          @AuthenticationPrincipal UserAccount user) {
        //SenderVo 채우기

        SendPaperVo sendPaperVo = new SendPaperVo();
        // 1.작성자 닉네임 받는 변수 만들기
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
            AddressVo address = new AddressVo();
            address.setReceiveNick(tempReceiver);
            addressArray.add(address);

            //ReceivePaper에 정보 미리 저장
            ReceivePaperVo receivePaperVo = new ReceivePaperVo();
            receivePaperVo.setSenderNick(senderNick);
            receivePaperVo.setReceivePaperContent(sendPaperContent);
            receivePaperVo.setReceiveNick(tempReceiver);
            receiverVos.add(receivePaperVo);
        }
        sendPaperVo.setReceiveInfo(addressArray);
        sendPaperVo.setSenderNick(senderNick);
        sendPaperVo.setPaperContent(sendPaperContent);

        //DB에 전송
        this.paperService.registerPaper(sendPaperVo,receiverVos);

        return "redirect:/paperPage";


    }

}
