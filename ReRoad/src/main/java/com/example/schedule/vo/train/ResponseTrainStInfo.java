package com.example.schedule.vo.train;

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
public class ResponseTrainStInfo {

	private CityTrainStInfoVo[] cityTrainStInfoVos;
	
}//class end
