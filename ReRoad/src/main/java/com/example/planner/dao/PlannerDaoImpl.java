package com.example.planner.dao;

import com.example.planner.vo.CheckListVO;
import com.example.planner.vo.PlannerVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("plannerDao")
public class PlannerDaoImpl implements PlannerDao{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<PlannerVO> selectPlanList(String userId) {
        return this.sqlSession.selectList("selectPlanList", userId);
    }

    @Override
    public List<PlannerVO> selectPastPlanList(String userId) {
        return this.sqlSession.selectList("selectPastPlanList", userId);
    }

    @Override
    public PlannerVO selectPlan(int planNo) {
        return this.sqlSession.selectOne("selectPlan", planNo);
    }

    @Override
    public void insertPlan(PlannerVO plan) {
        this.sqlSession.insert("insertPlan", plan);
    }

    @Override
    public int lastIdPlanner() {
        return this.sqlSession.selectOne("lastIdPlan");
    }

    @Override
    public void insertCheckList(CheckListVO checkList) {
        this.sqlSession.insert("insertChkList", checkList);
    }

    @Override
    public List<CheckListVO> selectCheckList(int planNo) {
        return this.sqlSession.selectList("selectChkList", planNo);
    }

    @Override
    public void deleteCheckList(int planNo) {
        this.sqlSession.delete("deleteChk", planNo);
    }

    @Override
    public void updatePlan(PlannerVO plan) {
        this.sqlSession.update("updatePlan", plan);
    }

    @Override
    public void deletePlan(int planNo) {
        this.sqlSession.delete("deletePlan", planNo);
    }
}
