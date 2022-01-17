package com.example.paper.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivePaperVo {

    private int receivePaperNo; // 수신함에서의 쪽지 고유 번호
    private int sendPaperNo; // 송신함에서의 쪽지 고유 번호
    private String senderId; // 작성한 회원의 아이디
    private String senderNick; // 작성한 회원의 닉네임
    private String receiveId; // 받는 회원의 아이디
    private String receiveNick; // 받는 회원의 닉네임
    private String receivePaperContent; // 쪽지 내용
    private String sendDate; // 작성 일자
    private int isRead; // 읽기 여부
}
