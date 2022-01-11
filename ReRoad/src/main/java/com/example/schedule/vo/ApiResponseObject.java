package com.example.schedule.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.example.schedule.vo.train.TrainScInfoVo;
import com.example.schedule.vo.train.TrainSetInfoVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 	API에서 응답한 entity객체
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseObject {

	private LinkedHashMap _embedded;
	private LinkedHashMap _links;
	private String pageNo;
	private String totalCnt;
	private ArrayList<TrainScInfoVo> scList;
	
	
}//class end
