package com.example.schedule.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.schedule.service.ExpBusScheduleService;
import com.example.schedule.service.TrainScheduleService;
import com.example.schedule.vo.UserScRequsetVo;
import com.example.schedule.vo.expBus.ExpBusScVo;
import com.example.schedule.vo.train.TrainScVo;

@Controller
public class ScheduleController {
	
	@Autowired
	TrainScheduleService trainScheduleService;
	
	@Autowired
	ExpBusScheduleService expBusScheduleService;
	
	//경로 기본
	private static final String PATH ="views/schedule/";
	
	//스케줄 조회
	@PostMapping("/retriveSchedule")
	public String retriveSchedule(Model model, UserScRequsetVo usrv)throws Exception{
		
		
		System.out.println(usrv.getVehiclType());
		
		System.out.println("usv: " + usrv.toString());
		//지역별 기차역리스트 add
        model.addAttribute("trainStList", trainScheduleService.retrieveTrainStinfo());
        //고속버스 터미널 리스트 add
        model.addAttribute("expTmlList", this.expBusScheduleService.getTmlInfo());
        
        //사용자가 선택한 정보들
        model.addAttribute("depLo", usrv.getDepLo()); //출발지 id
        model.addAttribute("arrLo", usrv.getArrLo()); //도착지 id
        model.addAttribute("depLoName", usrv.getDepLoName()); //출발지 이름
        model.addAttribute("arrLoName", usrv.getArrLoName()); //도착지 이름
        model.addAttribute("startDate", usrv.getStartDate()); //가는날 
        model.addAttribute("arrDate", usrv.getArrDate()); //오는날4
        model.addAttribute("vehiclType", usrv.getVehiclType());
        
        System.out.println("dll : " + usrv.getDepLo());
        System.out.println("dll : " + usrv.getArrLo());
        System.out.println("dl : " + usrv.getDepLoName());
        System.out.println("al : " + usrv.getArrLoName());
        System.out.println("sd : " + usrv.getStartDate());
        System.out.println("ad : " + usrv.getArrDate());
        
        
		//api에서 열차 스케줄 조회     
        if(usrv.getVehiclType().equals("train")) {
        	HashMap<String, Object> ScList =  this.trainScheduleService.parseUserRequestInfoBeforeResponeScList(usrv);
        	
        	
        	System.out.println("train gogogo");
        	
        	
			TrainScVo startSc = (TrainScVo)ScList.get("startSc");     
			//model에 스케줄 조회 add
			 model.addAttribute("startSc", startSc); //가는날 데이터
			 
			 //왕복일경우 왕복 정보 add
			 TrainScVo turnSc = (TrainScVo)ScList.get("turnSc");
			 if(turnSc != null) {
				 System.out.println("turnSc : " + turnSc.toString());
				 model.addAttribute("turnSc", turnSc); //가는날 데이터
			 	}//if end
        }//if end
        
        //api에서 고속버스 스케줄 조회
        if(usrv.getVehiclType().equals("expBus")) {
        	
        	System.out.println("exp gogogo");
        	
        	HashMap<String, Object> ScList =  this.expBusScheduleService.parseUserRequestInfoBeforeResponeScList(usrv);
        
        	ExpBusScVo startSc = (ExpBusScVo)ScList.get("startSc");     
			//model에 스케줄 조회 add
			 model.addAttribute("startSc", startSc); //가는날 데이터
			 
			 
			 System.out.println("startSc : " + startSc.toString());
			 
			 //왕복일경우 왕복 정보 add
			 ExpBusScVo turnSc = (ExpBusScVo)ScList.get("turnSc");
			 if(turnSc != null) {
				 System.out.println("turnSc : " + turnSc.toString());
				 model.addAttribute("turnSc", turnSc); 

				 System.out.println("turnSc : " + turnSc.toString());
				 
			 }//if end
			 
			 
			 
			 
        }//if end
        
        
		return PATH + "retriveSchedule";
		
	}//retriveSchedule() end
	

}//class end
