package com.example.notice.service;

import com.example.common.FileVO;
import com.example.common.SearchVO;
import com.example.notice.dao.NoticeDao;
import com.example.notice.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public NoticeVO retrieveNotice(int noticeNo) {
        NoticeVO notice = this.noticeDao.selectNotice(noticeNo);
        notice.setNoticeFileList(this.noticeDao.selectNoticeFileList(noticeNo));
        return  notice;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createNotice(NoticeVO notice) {
        this.noticeDao.insertNotice(notice);
        int no = this.noticeDao.lastIdNotice();
        for (FileVO file : notice.getNoticeFileList()) {
            file.setNoticeNo(no);
            this.noticeDao.insertNoticeFile(file);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void modifyNotice(NoticeVO notice) {
        this.noticeDao.updateNotice(notice);
        for (FileVO file : notice.getNoticeFileList()) {
            file.setNoticeNo(notice.getNoticeNo());
            this.noticeDao.insertNoticeFile(file);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeNotice(int noticeNo) {
        this.noticeDao.deleteNoticeFile(noticeNo);
        this.noticeDao.deleteNotice(noticeNo);
    }

    @Override
    public List<NoticeVO> retrieveNoticeList() {
        return this.noticeDao.selectNoticeList();
    }

    @Override
    public List<NoticeVO> searchNotice(SearchVO search) {
        String searchKey = search.getKeyfield();
        if(searchKey.equals("all")){
            System.out.println(this.noticeDao.selectSearchByAllNotice(search).size());
        return this.noticeDao.selectSearchByAllNotice(search);
        } else if (searchKey.equals("noticeTitle")) {
            return this.noticeDao.selectSearchByTitleNotice(search);
        } else return this.noticeDao.selectSearchByContentNotice(search);
    }

    @Override
    public void uphitCount(int noticeNo) {
        this.noticeDao.upHitCount(noticeNo);
    }

    @Override
    public FileVO retrieveNoticeFile(int fileNo) {
        return this.noticeDao.selectNoticeFile(fileNo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeOnlyNoticeFile(int fileNo) {
        this.noticeDao.deleteOnlyNoticeFile(fileNo);
    }

    @Override
    public List<NoticeVO> retrieveLastNotices() {
        return this.noticeDao.selectLastNotices();
    }
}
