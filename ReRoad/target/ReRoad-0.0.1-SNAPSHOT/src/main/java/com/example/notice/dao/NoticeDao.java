package com.example.notice.dao;

import com.example.common.FileVO;
import com.example.common.SearchVO;
import com.example.notice.vo.NoticeVO;

import java.util.List;

public interface NoticeDao {

    NoticeVO selectNotice(int noticeNo);
    void insertNotice(NoticeVO notice);
    void insertNoticeFile(FileVO file);
    void updateNotice(NoticeVO notice);
    void deleteNotice(int noticeNo);
    void deleteNoticeFile(int noticeNo);
    void deleteOnlyNoticeFile(int fileNo);
    List<NoticeVO> selectNoticeList();
    int lastIdNotice();
    List<NoticeVO> selectSearchByAllNotice(SearchVO search);
    List<NoticeVO> selectSearchByTitleNotice(SearchVO search);
    List<NoticeVO> selectSearchByContentNotice(SearchVO search);
    void upHitCount(int noticeNo);
    List<FileVO> selectNoticeFileList(int noticeNo);

    FileVO selectNoticeFile(int fileNo);
}
