package com.example.planner.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CheckListVO {
    private int checkListNo;
    private String checkListContent;
    private int ready;
    private int planNo;
}
