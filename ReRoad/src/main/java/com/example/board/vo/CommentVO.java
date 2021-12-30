package com.example.board.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CommentVO {
    private @NonNull int comNo;
    private @NonNull String comContent;
    private String comWdate;
    private String userId;
    private String userNick;
    private int boardNo;
}

