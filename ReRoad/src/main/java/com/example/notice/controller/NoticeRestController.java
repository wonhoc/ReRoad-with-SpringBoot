package com.example.notice.controller;



import com.example.common.FileVO;
import com.example.common.SearchVO;
import com.example.notice.service.NoticeService;
import com.example.notice.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class NoticeRestController {
    @Autowired
    private NoticeService noticeService;

    // 글목록 조회시 페이징을 위한 ajax
    @GetMapping("/getNoticeList")
    public List<NoticeVO> listNotice() {

        List<NoticeVO> list= this.noticeService.retrieveNoticeList();
        return list;
    }

    // 게시글 검색 ajax
    @GetMapping("/noticesearch")
    @ResponseBody
    public List<NoticeVO> searchNotice(@RequestParam String keyfield, @RequestParam String keyword) {

        SearchVO search = new SearchVO();
        search.setKeyfield(keyfield);
        search.setKeyword(keyword);
        List<NoticeVO> list= this.noticeService.searchNotice(search);

        return list;
    }

    //파일만 삭제(글 수정중 기존 파일 삭제할 경우)
    @DeleteMapping("/removeNoticeFile/{fileNo}/{noticeNo}")
    public List<FileVO> deleteOnlyFile(@PathVariable int fileNo, @PathVariable int noticeNo) {

        this.noticeService.removeOnlyNoticeFile(fileNo);
        //해당 파일 삭제 후 갱신된 파일 목록 보내기
        NoticeVO notice = this.noticeService.retrieveNotice(noticeNo);
        List<FileVO> files = notice.getNoticeFileList();
        return files;
    }
}