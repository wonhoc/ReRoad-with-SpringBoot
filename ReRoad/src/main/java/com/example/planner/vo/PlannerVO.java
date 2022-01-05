package com.example.planner.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class PlannerVO {
    private int planNo;
    private String userId;

    @NotEmpty(message = "여행 제목은 필수값입니다.")
    @Size(max=16, message = "여행 제목은 최대 16글자까지만 입력 가능합니다.")
    private String travelTitle;

    @NotEmpty(message = "여행지는 필수값입니다.")
    @Size(max=16, message = "여행지는 최대 16글자까지 등록가능합니다.")
    private String spot;

    @NotEmpty(message = "여행메모는 필수값입니다.")
    private String memo;

    @NotEmpty(message = "여행기간은 필수값입니다.")
    @Pattern(regexp = "^([12]\\\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\\\d|3[01]))$")
    private String startDate;

    @NotEmpty(message = "여행기간은 필수값입니다.")
    @Pattern(regexp = "^([12]\\\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\\\d|3[01]))$")
    private String arriveDate;

    @NotBlank(message = "체크리스트는 필수값입니다.")
    @Size(min =1, max = 50, message = "체크리스트는 최소1개이상, 최대50개까지만 등록 가능합니다.")
    private List<CheckListVO> checkList;
}
