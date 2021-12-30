package com.example.notice.dao;

import com.example.notice.vo.NoticeFileVO;
import com.example.notice.vo.NoticeSearchVO;
import com.example.notice.vo.NoticeVO;

import java.util.List;

public interface NoticeDao {

    NoticeVO selectNotice(int noticeNo);
    void insertNotice(NoticeVO notice);
    void insertNoticeFile(NoticeFileVO file);
    void updateNotice(NoticeVO notice);
    void deleteNotice(int noticeNo);
    void deleteNoticeFile(int noticeNo);
    void deleteOnlyNoticeFile(int fileNo);
    List<NoticeVO> selectNoticeList();
    int lastIdNotice();
    List<NoticeVO> selectSearchByAllNotice(NoticeSearchVO search);
    List<NoticeVO> selectSearchByTitleNotice(NoticeSearchVO search);
    List<NoticeVO> selectSearchByContentNotice(NoticeSearchVO search);
    void upHitCount(int noticeNo);
    List<NoticeFileVO> selectNoticeFileList(int noticeNo);

    NoticeFileVO selectNoticeFile(int fileNo);
}
