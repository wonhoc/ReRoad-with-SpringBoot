package com.example.schedule.vo.train;

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
public class TrainScInfoVo {
	
	
	/*
	 
	  api에서 스케줄 한번의 대한 정보를 가진 객체
	  
	 */
	
	private String adultcharge;	//기본운임
	private String arrplandtime;	//도착시간
	private String depplandtime;	//출발 시간
	private String traingradename;	//열차 종류
	private String trainno;			//해당 열차의 번호	
	private String spenTime;	//소요시간
	
}//class end
