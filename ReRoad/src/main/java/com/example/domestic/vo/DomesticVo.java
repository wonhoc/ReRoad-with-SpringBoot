package com.example.domestic.vo;

import com.example.board.vo.BoardFileVo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DomesticVo {
    private String domesticName;
    private String domesticRain;
    private String domesticTemper;
    private int domesticMain;

    private String thumbnailOrigin;
    private String thumbnailSystem;
    private int thumbnailSize;
    private String mainOrigin;
    private String mainSystem;
    private int mainSize;


    private MultipartFile mainImage;



}



