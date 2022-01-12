package com.example.planner.controller;

import com.example.notice.service.NoticeService;
import com.example.notice.vo.NoticeVO;
import com.example.planner.service.PlannerService;
import com.example.planner.vo.PlannerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PlannerRestController {
    @Autowired
    private PlannerService plannerService;

    // 게시글 조회 페이징을 위한 ajax
    @GetMapping("/getPlanList")
    public List<PlannerVO> listPlanner( @AuthenticationPrincipal User principal) {

        List<PlannerVO> list= this.plannerService.retrievePlanList(principal.getUsername());
        return list;
    }

    //지나간 플랜 목록 조회 페이징을 위한 ajax
    @GetMapping("/getPastPlanList")
    public List<PlannerVO> pastlistPlanner( @AuthenticationPrincipal User principal) {

        List<PlannerVO> list= this.plannerService.retrievePastPlanList(principal.getUsername());
        return list;
    }
}
