package com.example.paper.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressVo {

    private String receiveId; // 받는 회원의 아이디
    private String receiveNick; // 받는 회원의 닉네임
    private int isRead; // 읽기 여부
}
