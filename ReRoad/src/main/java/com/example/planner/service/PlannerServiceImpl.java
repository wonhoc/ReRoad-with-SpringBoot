package com.example.planner.service;

import com.example.common.FileVO;
import com.example.planner.dao.PlannerDao;
import com.example.planner.vo.CheckListVO;
import com.example.planner.vo.PlannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("plannerService")
public class PlannerServiceImpl implements PlannerService{

    @Autowired
    private PlannerDao plannerDao;

    @Override
    public List<PlannerVO> retrievePlanList(String userId) {
        return this.plannerDao.selectPlanList(userId);
    }

    @Override
    public List<PlannerVO> retrievePastPlanList(String userId) {
        return this.plannerDao.selectPastPlanList(userId);
    }

    @Override
    public PlannerVO retrievePlan(int planNo) {
        PlannerVO plan = this.plannerDao.selectPlan(planNo);
        plan.setCheckList(this.plannerDao.selectCheckList(planNo));
        return plan;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createPlan(PlannerVO plan) {
        this.plannerDao.insertPlan(plan);
        int no = this.plannerDao.lastIdPlanner();
        for (CheckListVO checkList : plan.getCheckList()) {
            checkList.setPlanNo(no);
            this.plannerDao.insertCheckList(checkList);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyPlan(PlannerVO plan) {
        this.plannerDao.deleteCheckList(plan.getPlanNo());
        this.plannerDao.updatePlan(plan);
        for (CheckListVO checkList : plan.getCheckList()) {
            checkList.setPlanNo(plan.getPlanNo());
            this.plannerDao.insertCheckList(checkList);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removePlan(int planNo) {
        this.plannerDao.deleteCheckList(planNo);
        this.plannerDao.deletePlan(planNo);
    }
}
