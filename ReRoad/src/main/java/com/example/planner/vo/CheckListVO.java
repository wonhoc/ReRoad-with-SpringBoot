package com.example.planner.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CheckListVO {
    private int checkListNo;
    @NotBlank(message = "체크리스트에 공백은 입력 불가합니다.")
    @Size(max=10 , message = "체크리스트의 내용은 10글자를 초과할 수 없습니다")
    private String checkListContent;
    @NotBlank(message = "준비여부는 null일 수 없습니다.")
    private int ready;
    private int planNo;
}
