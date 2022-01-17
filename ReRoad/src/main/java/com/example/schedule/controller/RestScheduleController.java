package com.example.schedule.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.schedule.service.ExpBusScheduleService;
import com.example.schedule.service.TrainScheduleService;
import com.example.schedule.vo.expBus.ExpBusScVo;
import com.example.schedule.vo.expBus.ExpBusSetInfoVo;
import com.example.schedule.vo.train.TrainScVo;
import com.example.schedule.vo.train.TrainSetInfoVo;

@RestController
public class RestScheduleController {
	
	@Autowired
	TrainScheduleService trainScheduleService;
	
	@Autowired
	ExpBusScheduleService expBusScheduleService;
	
	//경로 기본
	private static final String PATH ="views/schedule/";
	
	//열차 스케줄 조회 Ajax 요청
	@PostMapping("/getTrainSchedule") 
	public Map getTrainSchedule(@RequestBody TrainSetInfoVo setting) {
			
		System.out.println("setting" + setting.toString());
		 Map result = new HashMap<String, Object>();
		  
		 TrainScVo scVo = this.trainScheduleService.getTrainSc(setting);
		 
		System.out.println(scVo.toString());
		 
		 result.put("scList", scVo);
		 
		 return result;
		 
	  }//getTrainSchedule() end
	
	//버스 스케줄 조회 Ajax 요청
	@PostMapping("/getExpBusSchedule")
	public Map getExpBusSchedule(@RequestBody ExpBusSetInfoVo setting) {
		
		System.out.println("setting" + setting.toString());
		 Map result = new HashMap<String, Object>();
		  
		 ExpBusScVo scVo = this.expBusScheduleService.getExpBusSc(setting);
		 
		 System.out.println(scVo.toString());
		 
		 result.put("scList", scVo);
		 
		 return result;
		
	}//getExpBusSchedule() end

}//class end
