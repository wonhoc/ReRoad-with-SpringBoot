package com.example.domestic.controller;

import com.example.board.vo.BoardFileVo;
import com.example.board.vo.BoardVo;
import com.example.domestic.service.DomesticService;

import com.example.domestic.vo.DomesticVo;
import com.example.domestic.vo.WeatherVo;

import com.example.domestic.vo.WeatherVo2;
import com.example.schedule.service.ExpBusScheduleService;
import com.example.schedule.service.TrainScheduleService;
import com.example.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
public class DomesticController {

    @Autowired
    private DomesticService domesticService;
    
    @Autowired
    private TrainScheduleService trainScheduleService;
    
    @Autowired
    private ExpBusScheduleService expBusScheduleService;
    

    @GetMapping("/domestic/{domesticName}")
    public String domectic(@PathVariable String domesticName, Model model) throws Exception {

        List<WeatherVo2> weatherVo = this.domesticService.weather(domesticName);

        DomesticVo domestic = this.domesticService.manageDomestic(domesticName);

        System.out.println(weatherVo);

        model.addAttribute("weather",weatherVo);
        model.addAttribute("domestic",domestic);
        //지역별 기차역리스트 add
        model.addAttribute("trainStList", trainScheduleService.retrieveTrainStinfo());
        //고속버스 터미널 리스트 add
        model.addAttribute("expTmlList", this.expBusScheduleService.getTmlInfo());
        //선택한 지역이름 
        model.addAttribute("domesticName",domesticName);     
        model.addAttribute("content","views/domestic/domestic");

        return "/templates";
    }

    @GetMapping("/manageDomestic/{domesticName}")
    public String manageDomestic(@PathVariable String domesticName, Model model) throws Exception{

        List entireList = this.domesticService.boardmanageList();
        DomesticVo domestic = this.domesticService.manageDomestic(domesticName);

        System.out.println("domestic  "+ domestic);

        model.addAttribute("domestic", domestic);
        model.addAttribute("entireList", entireList);
        model.addAttribute("content","views/domestic/manageDomestic");


        return "/templates";

    }

    @PostMapping("/settingDomestic")
    public String settingDomestic(DomesticVo domestic, @RequestPart(value = "boardFileInput", required = false) List<MultipartFile> File,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes attributes, @AuthenticationPrincipal User principal) throws Exception {

        if (File.size() != 0) {

            List<BoardFileVo> boardFile = FileUtils.uploadFiles(File);

            String thumbnailOrigin="";
            String thumbnailSystem="";
            long thumbnailSize=0;

            for (BoardFileVo element : boardFile){
                if(element.getFileSize() != 0) {
                    thumbnailOrigin = element.getOriginalFileName();
                    thumbnailSystem = element.getSystemFileName();
                    thumbnailSize = element.getFileSize();
                }
            }

            domestic.setThumbnailOrigin(thumbnailOrigin);
            domestic.setThumbnailSystem(thumbnailSystem);
            domestic.setThumbnailSize(thumbnailSize);
            System.out.println("domestic   " + domestic);
        }

            this.domesticService.settingDome(domestic);

        return "redirect:/";
    }


}
