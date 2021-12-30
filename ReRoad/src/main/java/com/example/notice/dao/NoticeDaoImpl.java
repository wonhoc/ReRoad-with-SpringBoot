package com.example.notice.dao;

import com.example.notice.vo.NoticeFileVO;
import com.example.notice.vo.NoticeSearchVO;
import com.example.notice.vo.NoticeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("noticeDao")
public class NoticeDaoImpl implements NoticeDao{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public NoticeVO selectNotice(int noticeNo) {
        System.out.println(noticeNo);
       return this.sqlSession.selectOne("selectNotice", noticeNo);
    }

    @Override
    public void insertNotice(NoticeVO notice) {
        this.sqlSession.insert("insertNotice", notice);
    }

    @Override
    public void insertNoticeFile(NoticeFileVO file) {
        this.sqlSession.insert("insertNoticeFile", file);
    }

    @Override
    public void updateNotice(NoticeVO notice) {
        this.sqlSession.update("updateNotice", notice);
    }

    @Override
    public void deleteNotice(int noticeNo) {
        this.sqlSession.delete("deleteNotice",noticeNo);
    }

    @Override
    public void deleteNoticeFile(int noticeNo) {
        this.sqlSession.delete("deleteNoticeFiles", noticeNo);
    }

    @Override
    public void deleteOnlyNoticeFile(int fileNo) {
        this.sqlSession.delete("deleteOnlyFile", fileNo);
    }

    @Override
    public List<NoticeVO> selectNoticeList() {
        return this.sqlSession.selectList("selectNoticeList");
    }

    @Override
    public int lastIdNotice() {
        return this.sqlSession.selectOne("lastID");
    }

    @Override
    public List<NoticeVO> selectSearchByAllNotice(NoticeSearchVO search) {
        return this.sqlSession.selectList("searchAllNotice", search);
    }

    @Override
    public List<NoticeVO> selectSearchByTitleNotice(NoticeSearchVO search) {
        return this.sqlSession.selectList("searchTitleNotice", search);
    }

    @Override
    public List<NoticeVO> selectSearchByContentNotice(NoticeSearchVO search) {
        return this.sqlSession.selectList("searchContentNotice", search);
    }

    @Override
    public void upHitCount(int noticeNo) {
        this.sqlSession.update("upHitCount", noticeNo);
    }

    @Override
    public List<NoticeFileVO> selectNoticeFileList(int noticeNo) {
        return this.sqlSession.selectList("selectNoticeFiles" , noticeNo);
    }

    @Override
    public NoticeFileVO selectNoticeFile(int fileNo) {
        return this.sqlSession.selectOne("selectNoticeFile", fileNo);
    }

}
