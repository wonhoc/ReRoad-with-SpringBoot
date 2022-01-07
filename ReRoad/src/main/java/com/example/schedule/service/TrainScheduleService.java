package com.example.schedule.service;

import java.util.ArrayList;

import com.example.schedule.vo.CityTrainStInfoVo;

public interface TrainScheduleService {

	ArrayList<CityTrainStInfoVo> retrieveTrainStinfo() throws Exception;
	
}//interface end
