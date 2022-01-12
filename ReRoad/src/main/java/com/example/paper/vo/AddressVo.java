package com.example.paper.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressVo {

    private String receiveNick; // 받는 사람
    private int isRead; // 읽기 여부
}
