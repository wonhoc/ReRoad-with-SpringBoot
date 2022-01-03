package com.example.planner.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckListVO {
    private int checkListNo;
    private String checkListContent;
    private int ready;
    private int planNo;
}
