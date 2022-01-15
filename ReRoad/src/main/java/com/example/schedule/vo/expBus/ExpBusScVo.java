package com.example.schedule.vo.expBus;

import java.util.ArrayList;

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

/*
 
 api에서 스케줄 정보를 가져와서 ScList에 담음
 
 
 */
public class ExpBusScVo {
	
	private ArrayList<ExpBusScInfoVo> ScList; //스케줄 정보들
	private String depPlandTime;	//조회하는 날짜
	private String pageNo;			//조회하고 싶은 페이지 번호
	private String totalCnt;		//페이징 처리에 사용할 모든 정보 갯수
	private String numOfRows;		//한페이지에 제공되는 정보의 행 갯수 
	
}//class end
