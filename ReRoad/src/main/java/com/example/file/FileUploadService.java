package com.example.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

@MultipartConfig(fileSizeThreshold = 3000, maxFileSize = -3L, maxRequestSize = -3L)
@Service
public class FileUploadService {
    private String savePath = "/upload";

    public String restore(MultipartFile multipartFile, HttpServletRequest req) {

        String url = null;
        try{
            String photoOrigin = multipartFile.getOriginalFilename();
            String extName = photoOrigin.substring(photoOrigin.lastIndexOf("."),
                    photoOrigin.length());
            Long size = multipartFile.getSize();

            // 서버에서 저장 할 파일 이름
            String photoSys = genSaveFileName(extName);


            //file저장
            writeFile(multipartFile, photoSys);
            url = photoSys;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return url;
    }

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

    private boolean writeFile(MultipartFile multipartFile, String saveFileName)
            throws IOException{
        boolean result = false;
        byte[] data = multipartFile.getBytes();
        FileOutputStream fos = new FileOutputStream(savePath + "/" + saveFileName);
        fos.write(data);
        fos.close();

        return result;
    }
    public String boardRestore(MultipartFile multipartFile, HttpServletRequest req) {

        String url = null;

        try {
            String boardFileOrigin = multipartFile.getOriginalFilename();
            String extName = boardFileOrigin.substring(boardFileOrigin.lastIndexOf("."),
                    boardFileOrigin.length());
            Long size = multipartFile.getSize();

            // 서버에서 저장 할 파일 이름
            String boardFileSys = genSaveFileName(extName);

            //file저장
            writeFile(multipartFile, boardFileSys);

            url = boardFileSys;

        }catch(IOException e) {
            throw new RuntimeException(e);
        }

        return url;
    }
}
