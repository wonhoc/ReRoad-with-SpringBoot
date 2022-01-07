package com.example.util;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

//URL정보 만들고 InputStream객체 리턴
@NoArgsConstructor
public class ApiUrlBuilder {
	
	//옵션이 없는 경우
public InputStream ApiUrlBuild(String setUrl, String serviceKey, String setRequestMethod, String contentType) throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("");

		//url정보
	 	urlBuilder.append(setUrl + "?");
        //서비스키
	 	urlBuilder.append(URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey);
	 	
	 	//url
	 	URL url = new URL(urlBuilder.toString());
	 	//리턴할 InputStream 객체
	 	InputStream is;
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //요청방식
        conn.setRequestMethod(setRequestMethod);
        //요청 형식
        conn.setRequestProperty("Content-type", contentType);
        
        //정상적인 응답인지?
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        	  is = conn.getInputStream();
        }else {
        	
        	 throw new IOException(conn.getResponseCode() + " 정상적인 응답이 아닙니다.");
        	 
        }//if end
        
        //요청한 정보 응답 리턴
        return is;
        
	}//ApiUrlBuild() end
	

	//옵션이 있는 경우
	public InputStream ApiUrlBuild(String setUrl, String serviceKey, String setRequestMethod, String contentType, HashMap<String, String> appendOps) throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("");

		//url정보
	 	urlBuilder.append(setUrl + "?");
        //서비스키
	 	urlBuilder.append(URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey);
	 	
	 	//옵션들
	 	for(Map.Entry<String, String> op : appendOps.entrySet()) {
	 		 urlBuilder.append("&" + URLEncoder.encode(op.getKey(),"UTF-8") + "=" + URLEncoder.encode(op.getValue(), "UTF-8"));
	 	}//for end
	 	
	 	
	 	//url
	 	URL url = new URL(urlBuilder.toString()); 	
	 	//리턴할 InputStream 객체
	 	InputStream is;
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //요청방식
        conn.setRequestMethod(setRequestMethod);
        //요청 형식
        conn.setRequestProperty("Content-type", contentType);
        
        //정상적인 응답인지?
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        	  is = conn.getInputStream();
        }else {
        	
        	 throw new IOException(conn.getResponseCode() + " 정상적인 응답이 아닙니다.");
        	 
        }//if end
        
        //요청한 정보 응답 리턴
        return is;
        
	}//ApiUrlBuild() end
	
	
}//class end
