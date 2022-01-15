package com.example.schedule.vo.expBus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 
 사용자가 요청한 정보를 담은 객체
  
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpBusSetInfoVo {
	
	private String depTerminalId;	//출발지 터미널  ID
	private String arrTerminalId;	//도착지 터미널 ID
	private String depPlandTime;	//조회할 날짜
	private String pageNo;			//요청한 페이지 번호

}//class end
