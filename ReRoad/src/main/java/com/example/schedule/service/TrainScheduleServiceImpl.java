package com.example.schedule.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.schedule.vo.ApiResponseObject;
import com.example.schedule.vo.CityTrainStInfoVo;
import com.example.schedule.vo.TrainStInfoVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service("trainScheduleService")
public class TrainScheduleServiceImpl implements TrainScheduleService {
	
	private final RestTemplate restTemplate;
	
	//서버 DNS주소
	private final String SERVER_URL = "http://ec2-54-180-79-188.ap-northeast-2.compute.amazonaws.com";
	//도시별 기차역 정보를 요청 path
	private final String GET_TRAIN_INFO_URL = "/api/citytrainstinfo";
	
	
	public TrainScheduleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
       // this.restTemplate.setErrorHandler(new MyErrorHandler());
    }
	
	//도시별 기차역 조회
	@Override
	public ArrayList<CityTrainStInfoVo> retrieveTrainStinfo() throws Exception {
		ResponseEntity<ApiResponseObject> responseEntity = restTemplate.getForEntity(SERVER_URL + GET_TRAIN_INFO_URL, ApiResponseObject.class); 
		
		//System.out.println("class : " + responseEntity.getClass());
		
		String json = responseEntity.getBody().get_embedded().get("cityTrainStInfoVoes").toString();
		
		//System.out.println("json : " + json);
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		
		Type listType = new TypeToken<ArrayList<CityTrainStInfoVo>>(){}.getType();
		
		ArrayList<CityTrainStInfoVo> list = gson.fromJson(json, listType);		
		
		return list;
		
	}//getTrainStinfo() end

	
	
	
}//class end
