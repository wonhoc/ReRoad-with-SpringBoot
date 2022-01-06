package com.example.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ReportVo {

    @NonNull private int boardNo;
    private String userId;	//신고 당한 사람
    @NonNull private String reporter;
    private String boardTitle;

}
