package com.example.schedule.service;

import java.util.ArrayList;

import com.example.schedule.vo.TrainStInfoVo;

public interface TrainScheduleService {

	ArrayList<TrainStInfoVo> retrieveTrainStinfo() throws Exception;
	
}//interface end
