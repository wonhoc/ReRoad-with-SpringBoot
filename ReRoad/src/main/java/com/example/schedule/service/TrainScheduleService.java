package com.example.schedule.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.schedule.vo.UserScRequsetVo;
import com.example.schedule.vo.train.CityTrainStInfoVo;
import com.example.schedule.vo.train.TrainScVo;
import com.example.schedule.vo.train.TrainSetInfoVo;

public interface TrainScheduleService {

	ArrayList<CityTrainStInfoVo> retrieveTrainStinfo() throws Exception;
	HashMap<String, Object> parseUserRequestInfoBeforeResponeScList(UserScRequsetVo usrv);
	TrainScVo getTrainSc (TrainSetInfoVo setting);
	
}//interface end
