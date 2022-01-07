package com.example.planner.dao;

import com.example.planner.vo.CheckListVO;
import com.example.planner.vo.PlannerVO;

import java.util.List;
import java.util.zip.CheckedInputStream;

public interface PlannerDao {
    List<PlannerVO> selectPlanList(String userId);
    List<PlannerVO> selectPastPlanList(String userId);
    PlannerVO selectPlan(int planNo);
    void insertPlan(PlannerVO plan);
    int lastIdPlanner();
    void insertCheckList(CheckListVO checkList);
    List<CheckListVO> selectCheckList(int planNo);
    void deleteCheckList(int planNo);
    void updatePlan(PlannerVO plan);
    void deletePlan(int planNo);
}
