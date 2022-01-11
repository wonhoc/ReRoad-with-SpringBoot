package com.example.paper.vo;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SendPaperVo {

    private int sendPaperNo; // 보낸 쪽지 PK
    private String sendPaperContent; // 보낸 쪽지 내용
    private String sendPaperDate; // 쪽지 보낸 일자
    private String senderNick; // 보내는 사람 닉네임
    private ArrayList<AddressVo> receiverNicks; // 받는 사람 닉네임


}
