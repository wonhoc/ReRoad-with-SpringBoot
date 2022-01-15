package com.example.domestic.vo;

import lombok.Data;

@Data
public class WeatherVo {

    String domesticName;

    //강우량
    int rnSt3Am;
    int rnSt3Pm;

    int rnSt4Am;
    int rnSt4Pm;

    int rnSt5Am;
    int rnSt5Pm;

    int rnSt6Am;
    int rnSt6Pm;

    int rnSt7Am;
    int rnSt7Pm;

    int rnSt8;

    int rnSt9;


    //날씨
    String wf3Am;
    String wf3Pm;

    String wf4Am;
    String wf4Pm;

    String wf5Am;
    String wf5Pm;

    String wf6Am;
    String wf6Pm;

    String wf7Am;
    String wf7Pm;

    String wf8;

    String wf9;

    //기온
    private int taMin3;
    private int taMax3;

    private int taMin4;
    private int taMax4;

    private int taMin5;
    private int taMax5;

    private int taMin6;
    private int taMax6;

    private int taMin7;
    private int taMax7;

    private int taMin8;
    private int taMax8;
}
