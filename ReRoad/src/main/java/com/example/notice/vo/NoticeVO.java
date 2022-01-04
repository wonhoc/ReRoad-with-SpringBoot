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
    @NotEmpty(message = "공지 제목은 필수값입니다.")
    @Size(max = 16, message = "공지 제목은 최대 16글자입니다.")
    private String noticeTitle;
    @NotEmpty(message = "공지 내용은 필수값입니다.")
    private String noticeContent;
    private int hitCount;
    private String writeDate;

    @Size(max = 5, message = "파일은 최대 5개까지만 등록가능합니다")
    private  List<FileVO> noticeFileList = new ArrayList<>();
}