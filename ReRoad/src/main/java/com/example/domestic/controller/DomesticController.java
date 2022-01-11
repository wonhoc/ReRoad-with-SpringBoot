package com.example.domestic.controller;

import com.example.domestic.service.DomesticService;

import com.example.domestic.vo.WeatherVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class DomesticController {

    @Autowired
    private DomesticService domesticService;

    @GetMapping("/domestic")
    public String domectic(Model model) throws Exception {

        WeatherVo weatherVo = this.domesticService.weather("서울");

        model.addAttribute("weather",weatherVo);

        return "views/domestic/domestic";
    }

    @GetMapping("/manageDomestic")
    public String manageDomestic(Model model) throws Exception{

        List entireList = this.domesticService.boardmanageList();

        List mainList = this.domesticService.boardMain();

        model.addAttribute("entireList", entireList);
        model.addAttribute("mainList",mainList);


        return "views/domestic/manageDomestic";

    }

}
