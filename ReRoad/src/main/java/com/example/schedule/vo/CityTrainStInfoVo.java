package com.example.schedule.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor

/*
 	api에서 응답한 도시별 기차역 정보 객체
 */
public class CityTrainStInfoVo {
	
	String cityName;
	ArrayList<TrainStInfoVo> trainStInfo;
	
}//class end
