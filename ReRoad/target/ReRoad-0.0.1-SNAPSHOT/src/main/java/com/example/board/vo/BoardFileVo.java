package com.example.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardFileVo {
    private int fileNo;
    private String originalFileName;
    private String systemFileName;
    private long fileSize;
    private int boardNo;

    public BoardFileVo(String originalFileName, String systemFileName, long fileSize) {
    }
}
