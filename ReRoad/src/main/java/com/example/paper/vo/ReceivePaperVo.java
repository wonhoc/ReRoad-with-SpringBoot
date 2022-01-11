package com.example.paper.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ReceivePaperVo {

    private int recievePaperNo;
    private String writer;
    private String receiveNick;
    private String receivePaperContent;
    private String receiveDate;
    private int isRead;
}
