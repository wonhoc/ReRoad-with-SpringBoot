package com.example.domestic.vo;

import com.example.board.vo.BoardFileVo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class DomesticVo {
    private String prevdomesticName;
    private String domesticName;
    private String domesticRain;
    private String domesticTemper;
    private int domesticMain;

    private List<MultipartFile> fileList;
    private BoardFileVo file;

    private String thumbnailOrigin;
    private String thumbnailSystem;
    private long thumbnailSize;

}



