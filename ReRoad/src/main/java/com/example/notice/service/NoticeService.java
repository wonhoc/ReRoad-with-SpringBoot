package com.example.notice.service;

import com.example.common.FileVO;
import com.example.common.SearchVO;
import com.example.notice.vo.NoticeVO;

import java.util.List;

public interface NoticeService {

    NoticeVO retrieveNotice(int noticeNo);
    void createNotice(NoticeVO notice);

    void modifyNotice(NoticeVO notice);
    void removeNotice(int noticeNo);
    //void removeOnlyNoticeFile(int noticeNo);
    List<NoticeVO> retrieveNoticeList();

    List<NoticeVO> searchNotice(SearchVO search);

    void uphitCount(int noticeNo);

    FileVO retrieveNoticeFile(int fileNo);
    void removeOnlyNoticeFile(int fileNo);
}
