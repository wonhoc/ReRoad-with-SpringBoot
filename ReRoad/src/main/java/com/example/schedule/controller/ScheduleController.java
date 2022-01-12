package com.example.schedule.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.schedule.service.TrainScheduleService;
import com.example.schedule.vo.UserScRequsetVo;
import com.example.schedule.vo.train.TrainScVo;

@Controller
public class ScheduleController {
	
	@Autowired
	TrainScheduleService trainScheduleService;
	
	//경로 기본
	private static final String PATH ="views/schedule/";
	
	//스케줄 조회
	@PostMapping("/retriveSchedule")
	public String retriveSchedule(Model model, UserScRequsetVo usrv)throws Exception{
		
		System.out.println("usv: " + usrv.toString());
		//지역별 기차역리스트 add
        model.addAttribute("trainStList", trainScheduleService.retrieveTrainStinfo());
        //사용자가 선택한 정보들
        model.addAttribute("depLo", usrv.getDepLo()); //출발지 id
        model.addAttribute("arrLo", usrv.getArrLo()); //도착지 id
        model.addAttribute("depLoName", usrv.getDepLoName()); //출발지 이름
        model.addAttribute("arrLoName", usrv.getArrLoName()); //도착지 이름
        model.addAttribute("startDate", usrv.getStartDate()); //가는날 
        model.addAttribute("arrDate", usrv.getArrDate()); //오는날
        
        System.out.println("dll : " + usrv.getDepLo());
        System.out.println("dll : " + usrv.getArrLo());
        System.out.println("dl : " + usrv.getDepLoName());
        System.out.println("al : " + usrv.getArrLoName());
        System.out.println("sd : " + usrv.getStartDate());
        System.out.println("ad : " + usrv.getArrDate());
        
        
		//api에서 스케줄 조회
		HashMap<String, Object> ScList =  this.trainScheduleService.parseUserRequestInfoBeforeResponeScList(usrv);
		
		TrainScVo startSc = (TrainScVo)ScList.get("startSc");
		
		
		
		
		
		System.out.println("startSc : " + startSc.toString());
		
		
		//model에 스케줄 조회 add
		 model.addAttribute("startSc", startSc); //가는날 데이터
		 
		 //왕복일경우 왕복 정보 add
		 TrainScVo turnSc = (TrainScVo)ScList.get("turnSc");
		 if(turnSc != null) {
			 System.out.println("turnSc : " + turnSc.toString());
			 model.addAttribute("turnSc", turnSc); //가는날 데이터
		 }//if end
		 
		return PATH + "retriveSchedule";
		
	}//retriveSchedule() end
	

}//class end
