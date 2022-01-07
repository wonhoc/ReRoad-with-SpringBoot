package com.example.util;

import com.example.board.service.BoardService;
import com.example.board.vo.BoardFileVo;
import com.example.board.vo.BoardVo;
import com.example.common.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtils {

    public static final String UPLOAD_PATH = "C:\\upload/";

    public static List uploadFiles(List<MultipartFile> filelist) throws Exception {

        List<BoardFileVo> boardFileList = new ArrayList<>();



        for (MultipartFile file : filelist) {

            if (file.getSize() != 0) {

                String originalFileName = file.getOriginalFilename();

                String systemFileName = "";
                File f = new File(UPLOAD_PATH + File.separator + originalFileName);


                if (f.exists()) {
                    systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
                } else {
                    systemFileName = originalFileName;
                }

                long fileSize = file.getSize();

                String fullFilePath = UPLOAD_PATH + File.separator + systemFileName;


                Path path = Paths.get(fullFilePath).toAbsolutePath();

                BoardFileVo boardFile = new BoardFileVo();

                boardFile.setOriginalFileName(originalFileName);
                boardFile.setSystemFileName(systemFileName);
                boardFile.setFileSize(fileSize);


                file.transferTo(path.toFile());

                boardFileList.add(boardFile);
            }
        }

        return boardFileList;
    }
    }
