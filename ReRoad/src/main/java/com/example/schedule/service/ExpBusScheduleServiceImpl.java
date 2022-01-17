package com.example.schedule.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.schedule.vo.ApiResponseObject;
import com.example.schedule.vo.UserScRequsetVo;
import com.example.schedule.vo.expBus.ExpBusScInfoVo;
import com.example.schedule.vo.expBus.ExpBusScVo;
import com.example.schedule.vo.expBus.ExpBusSetInfoVo;
import com.example.schedule.vo.expBus.ExpBusTmlInfoVo;
import com.example.schedule.vo.train.CityTrainStInfoVo;
import com.example.schedule.vo.train.TrainSetInfoVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service("expBusScheduleService")
public class ExpBusScheduleServiceImpl implements ExpBusScheduleService{
	
		private final RestTemplate restTemplate;
	
		//서버 DNS주소
		@Value("${apiURL}")
		private String SERVER_URL;
		//도시별 터미널 정보 요청 path
		private final String GET_EXPBUS_TML_INFO = "/api/expbustmlinfo";	
		//버스 스케줄 조회 요청 path
		private final String GET_EXPBUS_SC = "/api/retriveexpbusschedule/{depTerminalId}/{arrTerminalId}/{depPlandTime}/{pageNo}";
		
		public ExpBusScheduleServiceImpl(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	       // this.restTemplate.setErrorHandler(new MyErrorHandler());
	    }
		
		//터미널 정보 조회
		@Override
		public ArrayList<ExpBusTmlInfoVo> getTmlInfo() {
			
			ResponseEntity<ApiResponseObject> responseEntity = restTemplate.getForEntity(SERVER_URL + GET_EXPBUS_TML_INFO, ApiResponseObject.class);
			
			ArrayList<ExpBusTmlInfoVo> ExpBusTmlList = (ArrayList<ExpBusTmlInfoVo>)responseEntity.getBody().get_embedded().get("expBusTmlInfoVoes");
			
			return ExpBusTmlList;
		}//getTmlInfo() end
		
		//유저가 보내온 정보에 따라서 조회할 정보들 구별하기
		@Override
		public HashMap<String, Object> parseUserRequestInfoBeforeResponeScList(UserScRequsetVo usrv) {
		
			System.out.println("expbus info begin");
			
			//리턴할객체
			HashMap<String, Object> scList = new HashMap<String, Object>();
			
				//열차 정보 조회하기		
				ExpBusSetInfoVo setting = new ExpBusSetInfoVo();
				
				//출발지 세팅
				setting.setDepTerminalId(usrv.getDepLo());
				//도착지 세팅
				setting.setArrTerminalId(usrv.getArrLo());
				//출발날짜 세팅
				setting.setDepPlandTime(usrv.getStartDate());
				//페이지 번호 세팅
				setting.setPageNo(usrv.getPageNo());
				
				//api에서 조회하고, 리턴할 객체에 add
				System.out.println("put setting");
				scList.put("startSc", getExpBusSc(setting));
				
				//왕복이라면(getArrDate가 none이 아니면) 출발지와 도착지를 서로 바꿔서 지정
				if(!usrv.getArrDate().equals("none")) {
					
					System.out.println("왕복입니다");
						
					ExpBusSetInfoVo settingTurn = new ExpBusSetInfoVo();
						
					//출발지 세팅
					settingTurn.setDepTerminalId(usrv.getArrLo());		
					//도착지 세팅
					settingTurn.setArrTerminalId(usrv.getDepLo());
					//오는날 세팅
					settingTurn.setDepPlandTime(usrv.getArrDate());
					//페이지 번호 선택
					settingTurn.setPageNo(usrv.getPageNo());
					//리턴할 객체에 add
					scList.put("turnSc", getExpBusSc(settingTurn));
					
				}else{
					System.out.println("왕복아님");
					scList.put("turnSc", null);
				}//end
				
			System.out.println("expbus info end");
			return scList;
				
		}//parseUserRequestInfoBeforeResponeScList() end
		
		//버스 스케줄 조회
		@Override
		public ExpBusScVo getExpBusSc(ExpBusSetInfoVo setting) {
			
			//리턴할 객체
			ExpBusScVo expBusSc = new ExpBusScVo();
			
			//넘길 값들 세팅
			HashMap<String, String> params = new HashMap<String, String>();
			
			params.put("pageNo", setting.getPageNo());
			params.put("depTerminalId", setting.getDepTerminalId());
			params.put("arrTerminalId", setting.getArrTerminalId());
			params.put("depPlandTime", setting.getDepPlandTime());
			
			ResponseEntity<ApiResponseObject> responseEntity = restTemplate.getForEntity(SERVER_URL + GET_EXPBUS_SC, ApiResponseObject.class, params);
			
			ArrayList<ExpBusScInfoVo> infos = (ArrayList<ExpBusScInfoVo>)responseEntity.getBody().getScList();
			
			//리턴할 객체에 set
			expBusSc.setScList(infos);
			//페이징처리에 필요한 값
			expBusSc.setTotalCnt(responseEntity.getBody().getTotalCnt());
			//현재 페이지 번호 
			expBusSc.setPageNo(responseEntity.getBody().getPageNo());
			//전체 페이지 번호
			expBusSc.setNumOfRows(responseEntity.getBody().getNumOfRows());
			
			return expBusSc;
		}//getExpBusSc() end
		
}//class end
