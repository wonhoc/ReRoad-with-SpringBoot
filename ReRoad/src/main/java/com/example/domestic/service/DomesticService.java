package com.example.domestic.service;


import com.example.domestic.dao.DomesticDao;
import com.example.domestic.vo.DomesticVo;
import com.example.domestic.vo.WeatherVo;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@Service("DomesticService")
public class DomesticService {

    @Autowired
    private DomesticDao domesticDao;

    public WeatherVo weather(String domesticName) throws Exception {

        DomesticVo domestic = this.domesticDao.selectRain(domesticName);

        String rain = domestic.getDomesticRain();

        SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMdd");

        Date time = new Date();

        String currentDate = dtf.format(time) + "0600";

        System.out.println("시간 " + currentDate);

        List list = new ArrayList();
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=WUXBUg4S5mLG5mO1HJPqS0dSiXjvftGjtK8npT57nv07Bgs4DvEp0Pp8tkoGjCBrtnc%2BZFQk8emlU%2BjuYrRP%2BA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(rain, "UTF-8")); /*11B0000 서울, 인천, 경기도 11D10000 등 (활용가이드 하단 참고자료 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc", "UTF-8") + "=" + URLEncoder.encode(currentDate, "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600(1800)-최근 24시간 자료만 제공*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String result;

        result = rd.readLine();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONObject weatherInfoResult = (JSONObject) jsonObject.get("response");
        JSONObject weatherReasult = (JSONObject) weatherInfoResult.get("body");
        JSONObject weatherItems = (JSONObject) weatherReasult.get("items");

        JSONArray weather = (JSONArray) weatherItems.get("item");

        WeatherVo rgWeather = new WeatherVo();

        for (int i = 0; i < weather.size(); i++) {
            JSONObject hi = (JSONObject) weather.get(i);


            rgWeather.setRnSt3Am((int) hi.get("rnSt3Am"));
            rgWeather.setRnSt3Pm((int) hi.get("rnSt3Pm"));

            rgWeather.setRnSt4Am((int) hi.get("rnSt4Am"));
            rgWeather.setRnSt4Pm((int) hi.get("rnSt4Pm"));

            rgWeather.setRnSt4Am((int) hi.get("rnSt5Am"));
            rgWeather.setRnSt4Pm((int) hi.get("rnSt5Pm"));

            rgWeather.setRnSt5Am((int) hi.get("rnSt6Am"));
            rgWeather.setRnSt5Pm((int) hi.get("rnSt6Pm"));

            rgWeather.setRnSt6Am((int) hi.get("rnSt7Am"));
            rgWeather.setRnSt6Am((int) hi.get("rnSt7Pm"));

            rgWeather.setRnSt8((int) hi.get("rnSt8"));
            rgWeather.setRnSt9((int) hi.get("rnSt9"));

            rgWeather.setWf3Am((String) hi.get("wf3Am"));
            rgWeather.setWf3Pm((String) hi.get("wf3Pm"));

            rgWeather.setWf4Am((String) hi.get("wf4Am"));
            rgWeather.setWf4Pm((String) hi.get("wf4Pm"));

            rgWeather.setWf5Am((String) hi.get("wf5Am"));
            rgWeather.setWf5Pm((String) hi.get("wf5Pm"));

            rgWeather.setWf6Am((String) hi.get("wf6Am"));
            rgWeather.setWf6Pm((String) hi.get("wf6Pm"));

            rgWeather.setWf7Am((String) hi.get("wf7Am"));
            rgWeather.setWf7Pm((String) hi.get("wf7Pm"));

            rgWeather.setWf8((String) hi.get("wf8"));
            rgWeather.setWf9((String) hi.get("wf9"));

        }

        String Temper = domestic.getDomesticTemper();

        System.out.println("Temper " +Temper);

        StringBuilder urlBuilder2 = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"); /*URL*/
        urlBuilder2.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=WUXBUg4S5mLG5mO1HJPqS0dSiXjvftGjtK8npT57nv07Bgs4DvEp0Pp8tkoGjCBrtnc%2BZFQk8emlU%2BjuYrRP%2BA%3D%3D"); /*Service Key*/
        urlBuilder2.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder2.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder2.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder2.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode(Temper, "UTF-8")); /*11B10101 서울, 11B20201 인천 등 (별첨 파일 참조)*/
        urlBuilder2.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(currentDate, "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력- YYYYMMDD0600(1800) 최근 24시간 자료만 제공*/
        URL url2 = new URL(urlBuilder2.toString());
        HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
        conn2.setRequestMethod("GET");
        conn2.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn2.getResponseCode());
        BufferedReader rd2;
        if(conn2.getResponseCode() >= 200 && conn2.getResponseCode() <= 300) {
            rd2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
        } else {
            rd2 = new BufferedReader(new InputStreamReader(conn2.getErrorStream()));
        }
        String result2;

        result2 = rd2.readLine();

        JSONParser jsonParser2 = new JSONParser();
        JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(result2);
        JSONObject weatherInfoResult2 = (JSONObject) jsonObject2.get("response");
        JSONObject weatherReasult2 = (JSONObject) weatherInfoResult2.get("body");
        JSONObject weatherItems2 = (JSONObject) weatherReasult2.get("items");

        System.out.println("weatherItems2 " +weatherItems2);

        JSONArray weather2 = (JSONArray) weatherItems2.get("item");



        for (int i = 0; i < weather2.size(); i++) {
            JSONObject hi = (JSONObject) weather2.get(i);

            rgWeather.setTaMin3((int) hi.get("taMin3"));
            rgWeather.setTaMax3((int) hi.get("taMax3"));

            rgWeather.setTaMin4((int) hi.get("taMin4"));
            rgWeather.setTaMax4((int) hi.get("taMax4"));

            rgWeather.setTaMin5((int) hi.get("taMin5"));
            rgWeather.setTaMax5((int) hi.get("taMax5"));

            rgWeather.setTaMin6((int) hi.get("taMin6"));
            rgWeather.setTaMax6((int) hi.get("taMax6"));

            rgWeather.setTaMin7((int) hi.get("taMin7"));
            rgWeather.setTaMax7((int) hi.get("taMax7"));

            rgWeather.setTaMin8((int) hi.get("taMin8"));
            rgWeather.setTaMax8((int) hi.get("taMax8"));
        }

        rgWeather.setDomesticName(domestic.getDomesticName());

        return rgWeather;
    }

    //메인화면 국내여행조회
    public List boardMain(){

        return this.domesticDao.selectDomesticMain();
    }

    //국내여행 관리창에서 조회
    public List boardmanageList(){
        return this.domesticDao.selectEntireDomestic();
    }

    public DomesticVo manageDomestic(String domesticName){

        return this.domesticDao.selectRain(domesticName);
    }

    public void settingDome(DomesticVo domestic){
    this.domesticDao.updateDomestic(domestic);
    }
}
