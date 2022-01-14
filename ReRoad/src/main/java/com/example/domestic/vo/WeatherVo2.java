package com.example.domestic.vo;

import lombok.Data;

@Data
public class WeatherVo2 {

    String date;

    //강우량
    int rnStAM;
    int rnStPM;

    //날씨
    String wfAm;
    String wfPm;

    //기온
    private int taMin;
    private int taMax;

    String date2;

    //강우량
    int rnStAM2;
    int rnStPM2;

    //날씨
    String wfAm2;
    String wfPm2;

    //기온
    private int taMin2;
    private int taMax2;

}
