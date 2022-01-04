package com.example.planner.controller;

import com.example.common.FileVO;
import com.example.notice.service.NoticeService;
import com.example.notice.vo.NoticeVO;
import com.example.planner.service.PlannerService;
import com.example.planner.vo.CheckListVO;
import com.example.planner.vo.PlannerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class PlannerController {
    @Autowired
    private PlannerService plannerService;

    //플랜 목록조회 페이지로 이동
    @GetMapping("/member/planlist")
    public String planList() {
        return "views/planner/planList.html";
    }

    // 플래너 상세보기
    @GetMapping("/member/plandetail")
    public String detailplan(int planNo, Model model) {

        PlannerVO plan = this.plannerService.retrievePlan(planNo);
        model.addAttribute("plan", plan);

        return "views/planner/planDetail";
    }

    // 플랜 작성
    // 작성폼불러오기
    @GetMapping("/member/planwriteform")
    public String noticeWriteForm( ) {

        return "views/planner/planWriteForm";
    }

    //게시글 작성
    @PostMapping("/member/writeplan")
    public String planWrite(@RequestParam("travelTitle") String travelTitle, @RequestParam("spot") String spot,
                              @AuthenticationPrincipal User principal, @RequestParam("startDate") String startDate,
                              @RequestParam("arriveDate") String arriveDate, @RequestParam("memo") String memo,
                              @RequestParam(value = "checkListContent", required = false) String[] checkListContents,
                              @RequestParam(value = "ready", required = false) int[] ready) {


        PlannerVO newPlan = new PlannerVO();
        newPlan.setTravelTitle(travelTitle);
        newPlan.setSpot(spot);
        newPlan.setStartDate(startDate);
        newPlan.setArriveDate(arriveDate);
        newPlan.setMemo(memo);
        newPlan.setUserId(principal.getUsername());
        List<CheckListVO> checkList = new ArrayList<CheckListVO>();


            for (int i = 0; i < ready.length; i++) {
                CheckListVO checkListVO = new CheckListVO();
                String isNull = checkListContents[i].replaceAll("/ /gi","");
                if (isNull != "" || !isNull.isEmpty() ) {
                    checkListVO.setCheckListContent(checkListContents[i]);

                    checkListVO.setReady(ready[i]);
                    checkList.add(i, checkListVO);
                }
            }

        newPlan.setCheckList(checkList);
        this.plannerService.createPlan(newPlan);

        return "redirect:/member/planlist";
    }


    //플랜 수정
    //수정 폼으로 이동
    @GetMapping("/member/planmodifyform/{planNo}")
    public String planModifyForm(@PathVariable int planNo,Model model) {

        PlannerVO plan = this.plannerService.retrievePlan(planNo);
        plan.setPlanNo(planNo);
        model.addAttribute("plan",plan);
        return "views/planner/planModifyForm";
    }

    //플래너 수정
    @PostMapping("/member/modifyplan")
    public String planModify(@RequestParam("planNo") int planNo,@RequestParam("travelTitle") String travelTitle,
                             @RequestParam("spot") String spot, @RequestParam("startDate") String startDate,
                             @RequestParam("arriveDate") String arriveDate, @RequestParam("memo") String memo,
                             @RequestParam(value = "checkListContent", required = false) String[] checkListContents,
                            @RequestParam(value = "ready", required = false) int[] ready) {

        PlannerVO newPlan = new PlannerVO();
        newPlan.setPlanNo(planNo);
        newPlan.setTravelTitle(travelTitle);
        newPlan.setSpot(spot);
        newPlan.setStartDate(startDate);
        newPlan.setArriveDate(arriveDate);
        newPlan.setMemo(memo);
        List<CheckListVO> checkList = new ArrayList<CheckListVO>();


            for (int i = 0; i < ready.length; i++) {
                CheckListVO checkListVO = new CheckListVO();
                checkListVO.setCheckListContent(checkListContents[i]);

                checkListVO.setReady(ready[i]);
                checkList.add(i, checkListVO);

            }
            newPlan.setCheckList(checkList);

        this.plannerService.modifyPlan(newPlan);

        return "redirect:/member/planlist";
    }

    //플래너 삭제
    @PostMapping("/member/plandelete/{planNo}")
    public String deletePlan(@PathVariable int planNo) {
        this.plannerService.removePlan(planNo);
        return "redirect:/member/planlist";
    }
}
