package com.example.reroad;

import com.example.notice.service.NoticeService;
import com.example.notice.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class mainController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/")
    public String main(Model model) {
        List<NoticeVO> noticeList  = this.noticeService.retrieveLastNotices();
        model.addAttribute("noticeList", noticeList);
        return "main";
    }

    @GetMapping("/test")
    public String error() {
        throw new RuntimeException("hello");
    }

}
