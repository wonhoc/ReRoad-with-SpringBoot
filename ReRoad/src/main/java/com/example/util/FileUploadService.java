package com.example.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

@Service
public class FileUploadService {
	
	private String savePath = "/upload";
	
	public String restore(MultipartFile multipartFile, HttpServletRequest req) {
		
		String url = null;
		
		try {
			String photoOrigin = multipartFile.getOriginalFilename();
			String extName = photoOrigin.substring(photoOrigin.lastIndexOf("."),
					photoOrigin.length());
			Long size = multipartFile.getSize();
			
			// 서버에서 저장 할 파일 이름
			String photoSys = genSaveFileName(extName);
			
		
			//file저장
			writeFile(multipartFile, photoSys);
			url = photoSys;
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		return url;
	}
	
	//현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
	}
	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName)
								throws IOException{
		boolean result = false;
			byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(savePath + "/" + saveFileName);
		fos.write(data);
		fos.close();
		
		return result;
	}
	
public String noticeStore(MultipartFile multipartFile, HttpServletRequest req) {
		
		String url = null;
		
		try {
			String fileOrigin = multipartFile.getOriginalFilename();
			String extName = fileOrigin.substring(fileOrigin.lastIndexOf("."),
					fileOrigin.length());
			Long size = multipartFile.getSize();
			
			// 서버에서 저장 할 파일 이름
			String fileSys = genSaveFileName(extName);
			
		
			//file저장
			writeFile(multipartFile, fileSys);
	
			url = fileSys;
	
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		return url;
	}
}
