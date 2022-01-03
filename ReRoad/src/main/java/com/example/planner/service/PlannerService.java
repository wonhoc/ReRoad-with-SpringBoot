package com.example.planner.service;


import com.example.planner.vo.PlannerVO;

import java.util.List;

public interface PlannerService {
    List<PlannerVO> retrievePlanList(String userId);
    PlannerVO retrievePlan(int planNo);
    void createPlan(PlannerVO plan);
    void modifyPlan(PlannerVO plan);
    void removePlan(int planNo);
}
