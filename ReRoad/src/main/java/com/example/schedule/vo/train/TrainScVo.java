package com.example.schedule.vo.train;

import java.util.ArrayList;

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
public class TrainScVo {	
	/*
	 
	  api에서 가져온 스케줄 정보를 ScList에 담는다.
	  
	*/
	private ArrayList<TrainScInfoVo> ScList;	//스케줄 정보들
	private String depPlandTime;	//조회하는 날짜
	private String pageNo;			//조회하고 싶은 페이지 번호
	private String totalCnt;		//페이징 처리에 필요한 해당 모든 정보들의 행 갯수
	private String numOfRows;		//한 페이지에 보여주는 정보의 행 갯수
	
	private String depLoName;
	private String arrLoName;
	
}//class end
