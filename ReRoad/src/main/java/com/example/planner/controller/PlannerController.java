package com.example.planner.controller;


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

import javax.validation.Valid;
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
    public String detailplan(int planNo, Model model, @AuthenticationPrincipal User principal) {
        //자신 외 다른 유저의 글 조회 방지
        String nowUser = principal.getUsername();
        String writeUser = this.plannerService.retrievePlan(planNo).getUserId();

        if (nowUser.equals(writeUser)) {
            PlannerVO plan = this.plannerService.retrievePlan(planNo);
            model.addAttribute("plan", plan);

            return "views/planner/planDetail";
        } else return "redirect:/member/planlist";

    }

    // 플랜 작성
    // 작성폼불러오기
    @GetMapping("/member/planwriteform")
    public String noticeWriteForm() {

        return "views/planner/planWriteForm";
    }

    //게시글 작성
    @PostMapping("/member/writeplan")
    public String planWrite(@Valid PlannerVO plan, @AuthenticationPrincipal User principal,
                            @RequestParam(value = "checkListContent", required = false) String[] checkListContents,
                            @RequestParam(value = "ready", required = false) int[] ready) {


        PlannerVO newPlan = new PlannerVO();
        newPlan.setTravelTitle(plan.getTravelTitle());
        newPlan.setSpot(plan.getSpot());
        newPlan.setStartDate(plan.getStartDate());
        newPlan.setArriveDate(plan.getArriveDate());
        newPlan.setMemo(plan.getMemo());
        newPlan.setUserId(principal.getUsername());
        List<CheckListVO> checkList = new ArrayList<CheckListVO>();


        for (int i = 0; i < ready.length; i++) {
            CheckListVO checkListVO = new CheckListVO();
            String isNull = checkListContents[i].replaceAll("/ /gi", "");
            if (isNull != "" || !isNull.isEmpty()) {
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
    public String planModifyForm(@PathVariable int planNo, Model model, @AuthenticationPrincipal User principal) {
        //다른 유저의 글 수정 방지
        String nowUser = principal.getUsername();
        String writeUser = this.plannerService.retrievePlan(planNo).getUserId();

        if (nowUser.equals(writeUser)) {
            PlannerVO plan = this.plannerService.retrievePlan(planNo);
            plan.setPlanNo(planNo);
            model.addAttribute("plan", plan);
            return "views/planner/planModifyForm";
        } else
            return "redirect:/member/planlist";
    }

    //플래너 수정
    @PostMapping("/member/modifyplan")
    public String planModify(@RequestParam("planNo") int planNo, @Valid PlannerVO plan,
                             @RequestParam(value = "checkListContent", required = false) String[] checkListContents,
                             @RequestParam(value = "ready", required = false) int[] ready) {

        PlannerVO newPlan = new PlannerVO();
        newPlan.setPlanNo(planNo);
        newPlan.setTravelTitle(plan.getTravelTitle());
        newPlan.setSpot(plan.getSpot());
        newPlan.setStartDate(plan.getStartDate());
        newPlan.setArriveDate(plan.getArriveDate());
        newPlan.setMemo(plan.getMemo());
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
