package com.example.domestic.controller;

import com.example.board.vo.BoardFileVo;
import com.example.board.vo.BoardVo;
import com.example.domestic.service.DomesticService;

import com.example.domestic.vo.DomesticVo;
import com.example.domestic.vo.WeatherVo;

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
import java.util.List;


@Controller
public class DomesticController {

    @Autowired
    private DomesticService domesticService;

    @GetMapping("/domestic/{domesticName}")
    public String domectic(@PathVariable String domesticName, Model model) throws Exception {

        WeatherVo weatherVo = this.domesticService.weather(domesticName);

        DomesticVo domestic = this.domesticService.manageDomestic(domesticName);


        model.addAttribute("weather",weatherVo);
        model.addAttribute("domestic",domestic);


        return "views/domestic/domestic";
    }

    @GetMapping("/manageDomestic/{domesticName}")
    public String manageDomestic(@PathVariable String domesticName, Model model) throws Exception{

        List entireList = this.domesticService.boardmanageList();
        DomesticVo domestic = this.domesticService.manageDomestic(domesticName);

        System.out.println("domestic  "+ domestic);

        model.addAttribute("domestic", domestic);
        model.addAttribute("entireList", entireList);



        return "views/domestic/manageDomestic";

    }

    @PostMapping("/settingDomestic")
    public String settingDomestic(DomesticVo domestic, @RequestPart(value = "boardFileInput", required = false) List<MultipartFile> File,
                                  BindingResult bindingResult, Model model,
                                  RedirectAttributes attributes, @AuthenticationPrincipal User principal ) throws Exception {

        System.out.println("File    : "+ File.size());
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
