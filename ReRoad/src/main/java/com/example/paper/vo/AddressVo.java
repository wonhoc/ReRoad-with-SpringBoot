package com.example.paper.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AddressVo {
    private String receivedNicks; // 받는 아이디
    private int isRead; // 조회 여부
}
