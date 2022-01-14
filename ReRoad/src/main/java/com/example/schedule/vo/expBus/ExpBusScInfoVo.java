package com.example.schedule.vo.expBus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpBusScInfoVo {
	
/*
 
 api에서 스케줄 하나의 대한 정보를 가진 객체
 
 */
	
	private String arrPlandTime;	//도착시간
	private String charge;			//기본운임	
	private String depPlandTime;	//출발시간	
	private String gradeNm;			//버스등급명
	private String spanTime;		//소요시간

}//classs end
