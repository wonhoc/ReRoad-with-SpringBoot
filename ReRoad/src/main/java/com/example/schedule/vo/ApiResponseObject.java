package com.example.schedule.vo;

import java.util.LinkedHashMap;

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
	private String numOfRows;
	private Object scList;


}//class end