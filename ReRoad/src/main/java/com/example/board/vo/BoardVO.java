package com.example.board.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardVO {
    private int boardNo;
    private  String userId;
    private String boardTitle;
    private String boardWdate;
    private String boardContent;
    private int boardCount;
    private int recomCount;
    private int commentCount;
    private String userNick;

    private BoardFileVO boardfile;
    private ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();



}
