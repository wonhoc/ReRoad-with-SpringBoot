package com.example.board.vo;

import com.example.common.FileVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

    private MultipartFile file;
    private List<MultipartFile> fileList;
    private BoardFileVo boardFile;
    private List<BoardFileVo> boardFiles;

    private List<CommentVo> commentList = new ArrayList<CommentVo>();
}
