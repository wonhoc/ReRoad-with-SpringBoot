package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
    private int fileNo;
    private String fileOrigin;
    private String fileSys;
    private long fileSize;
    private int noticeNo;
}
