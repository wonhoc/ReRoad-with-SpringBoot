package com.example.reroad;

import java.util.List;

import com.example.domestic.service.DomesticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.notice.service.NoticeService;
import com.example.notice.vo.NoticeVO;
import com.example.schedule.service.TrainScheduleService;

@Controller
public class mainController {
    @Autowired
    private NoticeService noticeService;
    
    @Autowired
    private TrainScheduleService trainScheduleService;

    @Autowired
    private DomesticService domesticService;

    @GetMapping("/")
    public String main(Model model) throws Exception {
        List<NoticeVO> noticeList  = this.noticeService.retrieveLastNotices();
        model.addAttribute("noticeList", noticeList);
        //지역별 기차역리스트 add
        model.addAttribute("trainStList", trainScheduleService.retrieveTrainStinfo());


        return "main";
    }

    @GetMapping("/test")
    public String error() {
        throw new RuntimeException("hello");
    }

}
