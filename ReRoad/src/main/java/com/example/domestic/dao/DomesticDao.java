package com.example.domestic.dao;

import com.example.domestic.vo.DomesticVo;

import java.util.List;

public interface DomesticDao {

    //국내여행메인
    List selectDomesticMain();//

    //국내여행지 전체조회
    List selectEntireDomestic();

    //국내여행
    DomesticVo selectRain(String domesticName);

    //국내여행 관리
    void updateDomestic(DomesticVo domestic);
}
