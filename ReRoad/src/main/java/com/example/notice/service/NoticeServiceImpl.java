package com.example.notice.service;

import com.example.notice.dao.NoticeDao;
import com.example.notice.vo.NoticeFileVO;
import com.example.notice.vo.NoticeSearchVO;
import com.example.notice.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createNotice(NoticeVO notice) {
        this.noticeDao.insertNotice(notice);
        int no = this.noticeDao.lastIdNotice();
        for (NoticeFileVO file : notice.getNoticeFileList()) {
            file.setNoticeNo(no);
            this.noticeDao.insertNoticeFile(file);
        }
    }


    @Override
    public void modifyNotice(NoticeVO notice) {
        this.noticeDao.updateNotice(notice);
        for (NoticeFileVO file : notice.getNoticeFileList()) {
            file.setNoticeNo(notice.getNoticeNo());
            this.noticeDao.insertNoticeFile(file);
        }
    }

    @Override
    public void removeNotice(int noticeNo) {
        this.noticeDao.deleteNoticeFile(noticeNo);
        this.noticeDao.deleteNotice(noticeNo);

    }

    @Override
    public List<NoticeVO> retrieveNoticeList() {
        return this.noticeDao.selectNoticeList();
    }


    @Override
    public List<NoticeVO> searchNotice(NoticeSearchVO search) {
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
    public NoticeFileVO retrieveNoticeFile(int fileNo) {
        return this.noticeDao.selectNoticeFile(fileNo);
    }

    @Override
    public void removeOnlyNoticeFile(int fileNo) {
        this.noticeDao.deleteOnlyNoticeFile(fileNo);
    }
}
