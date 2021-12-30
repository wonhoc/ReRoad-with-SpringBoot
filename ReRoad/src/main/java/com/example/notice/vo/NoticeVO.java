package com.example.notice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NoticeVO {

    private int noticeNo;
    private String userId;
    private String noticeTitle;
    private String noticeContent;
    private int hitCount;
    private String writeDate;

    private List<NoticeFileVO> noticeFileList = new ArrayList<>();
}