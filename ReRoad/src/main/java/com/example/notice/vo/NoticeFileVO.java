package com.example.notice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeFileVO {
    private int fileNo;
    private String fileOrigin;
    private String fileSys;
    private long fileSize;
    private int noticeNo;
}
