package com.example.schedule.vo;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 	스케줄 조회를 위해 유저가 선택한 정보를 담은 객체
 */



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserScRequsetVo {
	
	  @NotEmpty (message = "출발지를 선택해주세요.")
	  private String depLo;	//출발지
	  @NotEmpty (message = "도착지를 선택해주세요.")
	  private String arrLo;	//도착지
	  @NotEmpty (message = "가는 날짜를 선택해주세요.")
	  private String startDate;	//출발날짜 or 가는날
	  @NotEmpty (message = "오는 날짜를 선택해주세요.")
	  private String arrDate;	//오는날
	  @NotEmpty (message = "교통수단을 선택해주세요.")
	  private String vehiclType;	//교통수단
	  @NotEmpty (message = "유효하지 않은 페이지 번호입니다.")
	  private String pageNo;
	  
	  private String depLoName;
	  private String arrLoName;
	  
}//class end
