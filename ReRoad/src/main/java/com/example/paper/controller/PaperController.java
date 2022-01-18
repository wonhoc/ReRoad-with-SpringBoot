package com.example.paper.controller;

import com.example.member.vo.UserAccount;
import com.example.member.vo.UserVo;
import com.example.paper.dao.SendPaperDao;
import com.example.paper.service.PaperService;
import com.example.paper.vo.AddressVo;
import com.example.paper.vo.ReceivePaperVo;
import com.example.paper.vo.SendPaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PaperController {

    @Autowired
    private PaperService paperService;

    // 쪽지 보내기 페이지로 이동
    @GetMapping("/writePaper")
    public String goWritePaper(Model model) {
        model.addAttribute("content","views/paper/writePaper");
        return "/templates";
    }

    // 보낸 쪽지함 이동 시 리스트 수신
    @GetMapping("/sendPaper")
    public String sendPaperList(Model model, @AuthenticationPrincipal UserAccount user) {

        String senderId = user.getUser().getUserId();
        ArrayList<SendPaperVo> sendPaperList =
                (ArrayList<SendPaperVo>) this.paperService.retrieveSendPaperList(senderId);

        model.addAttribute("sendPaperList",sendPaperList);
        model.addAttribute("content","views/paper/sendPaper");

        return "/templates";

    }

    //받은 쪽지함 이동 시 리스트 수신
    @GetMapping("/receivePaper")
    public String goReceive(Model model, @AuthenticationPrincipal UserAccount user) {

        String receiveId = user.getUser().getUserId();

        List<ReceivePaperVo> receivePaperList = this.paperService.retrieveReceivePaperList(receiveId);
        model.addAttribute("receivePaperList",receivePaperList);
        model.addAttribute("content","views/paper/receivePaper");
        return "/templates";
    }


    // 작성한 쪽지 내용 저장
    @PostMapping("/sendNewPaper")
    public String sendPaper(@RequestParam("receiveNick") String receiveNick,
                            @RequestParam("sendPaperContent") String sendPaperContent,
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
            receivePaperVo.setReceivePaperContent(sendContent);
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

        model.addAttribute("content","views/paper/writePaper");

        return "/templates";
    }
    // 보낸 쪽지 리스트에서 상세 조회
    @GetMapping("/sendPaperDetail/{sendPaperNo}")
    public String sendPaperDetail(@PathVariable int sendPaperNo, Model model) {
        SendPaperVo sendPaper = this.paperService.retrieveSendPaper(sendPaperNo);
        model.addAttribute("sendPaper",sendPaper);
        model.addAttribute("content","views/paper/sendPaperDetail");
        return "/templates";


    }

    //받은 쪽지 리스트에서 상세 조회
    @GetMapping("/receivePaperDetail/{receivePaperNo}/{isRead}")
    public String receivePaperDetail(@AuthenticationPrincipal UserAccount user, @PathVariable int receivePaperNo,@PathVariable int isRead, Model model) {
        HashMap<String,Object> receivePaperMap = new HashMap<String,Object>();
        String userId = user.getUser().getUserId();

        receivePaperMap.put("receiveId",userId);
        receivePaperMap.put("receivePaperNo",receivePaperNo);
        ReceivePaperVo receivePaper = this.paperService.retrieveReceivePaper(receivePaperMap);

        // 읽음 상태가 0일 경우 1로 바꿔주기
        if(isRead == 0) {
            this.paperService.updateRead(receivePaperNo,userId);
        }
        model.addAttribute("receivePaper",receivePaper);
        model.addAttribute("content","views/paper/receivePaperDetail");
        return "/templates";
    }
    // 보낸 쪽지 리스트에서 삭제
    @PostMapping("/removeSendPaper")
    public String removeSendPaper(@RequestParam int[] removeCheckBox, @AuthenticationPrincipal UserAccount user, Model model) {
        // 체크박스 선택된 쪽지 삭제
        this.paperService.removeSendPaper(removeCheckBox);

        // 닉네임 값을 다시 받아와서 리스트 생성 후 페이지로 이동
        String senderId = user.getUser().getUserId();
        String senderNick = user.getUser().getUserNick();
        ArrayList<SendPaperVo> sendPaperList =
                (ArrayList<SendPaperVo>) this.paperService.retrieveSendPaperList(senderNick);

        model.addAttribute("sendPaperList",sendPaperList);
        model.addAttribute("content","views/paper/sendPaper");

        return "/templates";
    }
    //받은 쪽지 리스트에서 삭제
    @PostMapping("/removeReceivePaper")
    public String removeReceivePaper(HttpServletRequest req, @AuthenticationPrincipal UserAccount user, Model model) {
        String userId = user.getUser().getUserId();

        HashMap<String,Object> deleteMap = new HashMap<String,Object>();

        String[] removeCheckedBox = req.getParameterValues("removeCheckBox");
        deleteMap.put("receiveId",userId);
        for(String numbers:removeCheckedBox) {
            //receivePaperNo값
            int receivePaperNo = Integer.parseInt(numbers.substring(0,numbers.indexOf(",")));
            //isRead값
            int isRead = Integer.parseInt(numbers.substring(numbers.indexOf(",")+1, numbers.length()));
            deleteMap.put("receivePaperNo",receivePaperNo);

            //isRead가 1일 경우 바로 삭제
            if(isRead == 1) {
                this.paperService.removeReceivePaper(deleteMap);
                //isReand가 0일 경우 업데이트 한 후 삭제
            } else {
                this.paperService.updateRead(receivePaperNo,userId);
                this.paperService.removeReceivePaper(deleteMap);

            }

        }
        // 받은 메시지 다시 받은 후 화면 전환
        List<ReceivePaperVo> receivePaperList = this.paperService.retrieveReceivePaperList(userId);
        model.addAttribute("receivePaperList",receivePaperList);
        model.addAttribute("content","views/paper/receivePaper");
        return "/templates";

    }
    //보낸 페이지 상세 조회에서 삭제
    @PostMapping("removeSendPaperDetail")
    public String removeDetailSendPaper(@RequestParam int sendPaperNo, @AuthenticationPrincipal UserAccount user, Model model) {
        int[] sendPaperNoS = { sendPaperNo };
        this.paperService.removeSendPaper(sendPaperNoS);

        // 닉네임 값을 다시 받아와서 리스트 생성 후 페이지로 이동
        String senderNick = user.getUser().getUserNick();
        ArrayList<SendPaperVo> sendPaperList =
                (ArrayList<SendPaperVo>) this.paperService.retrieveSendPaperList(senderNick);

        model.addAttribute("sendPaperList",sendPaperList);
        model.addAttribute("content","views/paper/sendPaper");

        return "/templates";
    }

    // 받은 페이지 상세 조회에서 삭제
    @PostMapping("/removeReceivePaperDetail")
    public String removeDetailReceivePaper (@RequestParam int receivePaperNo,@AuthenticationPrincipal UserAccount user, Model model) {
        HashMap<String,Object> deleteMap = new HashMap<String,Object>();

        String userId = user.getUser().getUserId();
        deleteMap.put("receiveId", userId);
        deleteMap.put("receivePaperNo", receivePaperNo);

        this.paperService.removeReceivePaper(deleteMap);

        // 받은 메시지 다시 받은 후 화면 전환
        List<ReceivePaperVo> receivePaperList = this.paperService.retrieveReceivePaperList(userId);
        model.addAttribute("receivePaperList",receivePaperList);
        model.addAttribute("content","views/paper/receivePaper");
        return "/templates";
    }





}
