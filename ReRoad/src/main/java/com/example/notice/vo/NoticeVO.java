package com.example.notice.vo;

import com.example.common.FileVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class NoticeVO {
    private int noticeNo;
    private String userId;
    @NotEmpty(message = "제목은 필수값입니다.")
    @Size(max = 16, message = "최대 16글자입니다.")
    private String noticeTitle;
    @NotEmpty(message = "본문은 필수값입니다.")
    private String noticeContent;
    private int hitCount;
    private String writeDate;
    private  List<FileVO> noticeFileList = new ArrayList<>();
}