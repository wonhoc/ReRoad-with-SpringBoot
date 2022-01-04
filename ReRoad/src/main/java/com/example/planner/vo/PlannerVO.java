package com.example.planner.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlannerVO {
    private int planNo;
    private String userId;
    private String travelTitle;
    private String spot;
    private String memo;
    private String startDate;
    private String arriveDate;

    private List<CheckListVO> checkList;
}
