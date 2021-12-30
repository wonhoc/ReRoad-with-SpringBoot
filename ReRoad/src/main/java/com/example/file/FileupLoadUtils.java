package com.example.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class FileupLoadUtils {
    public static String UPLOAD_PATH = "";

    public static ArrayList<String> upload(Part part, HttpServletRequest request, String path) throws Exception{
        UPLOAD_PATH = request.getServletContext().getRealPath("/") + "/upload" +path;

        ArrayList<String> fileName = new ArrayList<String>();

        String originalFileName = part.getSubmittedFileName();

        File file = new File(UPLOAD_PATH + "/" + originalFileName);
        String systemFileName = "";

        if (file.exists()) {
            systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + UUID.randomUUID()
                    + originalFileName.substring(originalFileName.lastIndexOf("."));
        } else {
            systemFileName = originalFileName;
        }

        part.write(UPLOAD_PATH + "/" + systemFileName);
        part.delete();

        fileName.add(originalFileName);
        fileName.add(systemFileName);

        return fileName;
    }
}
