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
import com.example.schedule.vo.train.CityTrainStInfoVo;
import com.example.schedule.vo.train.TrainScInfoVo;
import com.example.schedule.vo.train.TrainScVo;
import com.example.schedule.vo.train.TrainSetInfoVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service("trainScheduleService")
public class TrainScheduleServiceImpl implements TrainScheduleService {

	private final RestTemplate restTemplate;

	//서버 DNS주소
	@Value("${apiURL}")
	private String SERVER_URL;
	//도시별 기차역 정보를 요청 path
	private final String GET_TRAIN_INFO_URL = "/api/citytrainstinfo";
	//기차 스케줄 조회 요청 path
	private final String GET_TRAIN_SC = "/api/retrivetrainschedule/{pageNo}/{depPlaceId}/{arrPlaceId}/{depPlandTime}";


	public TrainScheduleServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		// this.restTemplate.setErrorHandler(new MyErrorHandler());
	}

	//도시별 기차역 조회
	@Override
	public ArrayList<CityTrainStInfoVo> retrieveTrainStinfo() throws Exception {
		ResponseEntity<ApiResponseObject> responseEntity = restTemplate.getForEntity(SERVER_URL + GET_TRAIN_INFO_URL, ApiResponseObject.class);

		String json = responseEntity.getBody().get_embedded().get("cityTrainStInfoVoes").toString();

		//System.out.println("json : " + json);


		Gson gson = new GsonBuilder().setPrettyPrinting().create();


		Type listType = new TypeToken<ArrayList<CityTrainStInfoVo>>(){}.getType();

		ArrayList<CityTrainStInfoVo> list = gson.fromJson(json, listType);

		return list;

	}//getTrainStinfo() end

	//유저가 보내온 정보에 따라서 조회할 정보들 구별하기
	@Override
	public HashMap<String, Object> parseUserRequestInfoBeforeResponeScList(UserScRequsetVo usrv) {

		System.out.println("train info begin");

		//리턴할객체
		HashMap<String, Object> scList = new HashMap<String, Object>();

		//열차 정보 조회하기
		TrainSetInfoVo setting = new TrainSetInfoVo();

		//출발지 세팅
		setting.setDepPlaceId(usrv.getDepLo());
		//도착지 세팅
		setting.setArrPlaceId(usrv.getArrLo());
		//출발날짜 세팅
		setting.setDepPlandTime(usrv.getStartDate());
		//페이지 번호 세팅
		setting.setPageNo(usrv.getPageNo());

		//api에서 조회하고, 리턴할 객체에 add
		System.out.println("put setting");
		scList.put("startSc",getTrainSc(setting));

		//왕복이라면(getArrDate가 none이 아니면) 출발지와 도착지를 서로 바꿔서 지정
		if(!usrv.getArrDate().equals("none")) {

			System.out.println("왕복입니다");

			TrainSetInfoVo settingTurn = new TrainSetInfoVo();

			//출발지 세팅
			settingTurn.setDepPlaceId(usrv.getArrLo());
			//도착지 세팅
			settingTurn.setArrPlaceId(usrv.getDepLo());
			//오는날 세팅
			settingTurn.setDepPlandTime(usrv.getArrDate());
			//페이지 번호 선택
			settingTurn.setPageNo(usrv.getPageNo());
			//리턴할 객체에 add
			scList.put("turnSc", getTrainSc(settingTurn));

		}else{
			System.out.println("왕복아님");
			scList.put("turnSc", null);
		}//end

		System.out.println("train info end");
		return scList;

	}//parseUserRequestInfoBeforeResponeScList() end

	//기차 스케줄 조회
	@Override
	public TrainScVo getTrainSc(TrainSetInfoVo setting) {

		System.out.println("call getTrainSc");

		//리턴할 객체
		TrainScVo trainSc = new TrainScVo();

		//넘길 값들 세팅
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("pageNo", setting.getPageNo());
		params.put("depPlaceId", setting.getDepPlaceId());
		params.put("arrPlaceId", setting.getArrPlaceId());
		params.put("depPlandTime", setting.getDepPlandTime());

		ResponseEntity<ApiResponseObject> responseEntity = restTemplate.getForEntity(SERVER_URL + GET_TRAIN_SC, ApiResponseObject.class, params);

		System.out.println("responseEntity : " +  responseEntity.toString());

		System.out.println("get json");
		ArrayList<TrainScInfoVo> list = (ArrayList<TrainScInfoVo>)responseEntity.getBody().getScList();

		//리턴할 객체에 set
		//스케즐 정보 set
		trainSc.setScList(list);
		//페이징 처리에 필요한 모든 정보수
		trainSc.setTotalCnt(responseEntity.getBody().getTotalCnt());
		//페이징 처리에 필요한 조회한 현재 페이지 번호
		trainSc.setPageNo(responseEntity.getBody().getPageNo());

		trainSc.setNumOfRows((responseEntity.getBody().getNumOfRows()));

		return trainSc;

	}//getTrainSc() end





}//class end