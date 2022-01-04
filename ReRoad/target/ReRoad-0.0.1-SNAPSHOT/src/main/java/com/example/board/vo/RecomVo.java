package com.example.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecomVo {
    private String userId;
    private int boardNo;
    private int recomNo;
}