package com.example.notice.service;

import com.example.notice.vo.NoticeFileVO;
import com.example.notice.vo.NoticeSearchVO;
import com.example.notice.vo.NoticeVO;

import java.util.List;

public interface NoticeService {

    NoticeVO retrieveNotice(int noticeNo);
    void createNotice(NoticeVO notice);

    void modifyNotice(NoticeVO notice);
    void removeNotice(int noticeNo);
    //void removeOnlyNoticeFile(int noticeNo);
    List<NoticeVO> retrieveNoticeList();

    List<NoticeVO> searchNotice(NoticeSearchVO search);

    void uphitCount(int noticeNo);

    NoticeFileVO retrieveNoticeFile(int fileNo);
    void removeOnlyNoticeFile(int fileNo);
}
