package com.example.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardFileVO {
    private int boardfileNo;
    private String boardfileOrigin;
    private String boardfileSys;
    private long boardfileSize;
    private int boardNo;
    private String boardfileType;
}
