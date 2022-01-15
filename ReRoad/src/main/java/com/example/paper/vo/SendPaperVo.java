package com.example.paper.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendPaperVo {

    private int sendPaperNo; // 쪽지 고유 번호
    private String senderId; // 쪽지 쓴 회원의 아이디
    private String senderNick; // 쪽지 쓴 회원의 닉네임
    private ArrayList<AddressVo> receiveInfo; //받는 사람과 확연 여부
    private String paperContent; // 쪽지 내용
    private String sendDate; // 작성 일자
}
