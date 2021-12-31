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

    @Autowired



    // 게시글 조회 ajax
    @GetMapping("/getNoticeList")
    public @ResponseBody List<NoticeVO> listNotice() {
        List<NoticeVO> list= this.noticeService.retrieveNoticeList();

        return list;
    }

    // 게시글 검색 ajax
    @GetMapping("/noticesearch")
    @ResponseBody
    public List<NoticeVO> searchNotice(@RequestParam String keyfield,
                                       @RequestParam String keyword) {
        SearchVO search = new SearchVO();
        search.setKeyfield(keyfield);
        System.out.println(keyword);
        search.setKeyword(keyword);
        List<NoticeVO> list= this.noticeService.searchNotice(search);

        return list;
    }
    //공지사항 삭제
    @DeleteMapping("/admin/deleteNotice/{noticeNo}")
    public void deleteNotice(@PathVariable int noticeNo) {
        this.noticeService.removeNotice(noticeNo);

    }

    //파일만 삭제
    @DeleteMapping("/removeNoticeFile/{fileNo}/{noticeNo}")
    public List<FileVO> deleteOnlyFile(@PathVariable int fileNo, @PathVariable int noticeNo) {
       this.noticeService.removeOnlyNoticeFile(fileNo);

      NoticeVO notice = this.noticeService.retrieveNotice(noticeNo);
      List<FileVO> files = notice.getNoticeFileList();
      return files;
    }

}
