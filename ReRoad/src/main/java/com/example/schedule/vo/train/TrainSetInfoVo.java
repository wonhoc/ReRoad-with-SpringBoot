package com.example.schedule.vo.train;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrainSetInfoVo {
	
	@NotEmpty(message = "페이지 번호를 입력해주세요.")
	private String pageNo;	//페이지 번호
	@NotEmpty(message = "출발지를 선택해주세요.")
	@Max(value = 9, message = "유효하지 않은 역 정보입니다.")
	private String depPlaceId;
	@NotEmpty(message = "도착지를 선택해주세요.")
	@Max(value = 9, message = "유효하지 않은 역 정보입니다.")
	private String arrPlaceId;
	@NotEmpty(message = "출발 날짜를 선택해주세요.")
	private String depPlandTime;
	
	

}//class end
