package com.example.board.vo;

import com.example.common.FileVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class BoardVo {

    private int boardNo;
    private  String userId;
    private String boardTitle;
    private String boardWdate;
    private String boardContent;
    private int boardCount;
    private int recomCount;
    private int commentCount;
    private String userNick;


    private FileVO boardfile = new FileVO();
    private ArrayList<CommentVo> commentList = new ArrayList<CommentVo>();
}
