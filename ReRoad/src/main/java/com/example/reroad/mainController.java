package com.example.reroad;

import java.util.List;

import com.example.domestic.service.DomesticService;
import com.example.domestic.vo.DomesticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.notice.service.NoticeService;
import com.example.notice.vo.NoticeVO;
import com.example.schedule.service.ExpBusScheduleService;
import com.example.schedule.service.TrainScheduleService;

@Controller
public class mainController {
    @Autowired
    private NoticeService noticeService;
    
    @Autowired
    private TrainScheduleService trainScheduleService;
    
    @Autowired
    private ExpBusScheduleService expBusScheduleService;

    @Autowired
    private DomesticService domesticService;

    @GetMapping("/")
    public String main(Model model) throws Exception {
        List<NoticeVO> noticeList  = this.noticeService.retrieveLastNotices();
        model.addAttribute("noticeList", noticeList);
        //지역별 기차역리스트 add
        model.addAttribute("trainStList", this.trainScheduleService.retrieveTrainStinfo());
        //고속버스 터미널 리스트 add
        model.addAttribute("expTmlList", this.expBusScheduleService.getTmlInfo());
        
        List domestic = this.domesticService.boardMain();

        System.out.println("List : " + domestic);

        model.addAttribute("domestic", domestic);

        return "main";
    }

    @GetMapping("/test")
    public String error() {
        throw new RuntimeException("hello");
    }

}
