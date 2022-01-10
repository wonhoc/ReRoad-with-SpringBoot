package com.example.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {
	
	//경로 기본
	private static final String PATH ="views/schedule/";
	
	//스케줄 조회
	@GetMapping("/retriveSchedule")
	public String retriveSchedule(@RequestParam String depLo) {
		
		
		return PATH + "retriveSchedule";
		
	}//retriveSchedule() end
	

}//class end
