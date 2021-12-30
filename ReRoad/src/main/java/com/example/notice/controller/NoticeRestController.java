package com.example.notice.controller;



import com.example.notice.service.NoticeService;
import com.example.notice.vo.NoticeFileVO;
import com.example.notice.vo.NoticeSearchVO;
import com.example.notice.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
public class NoticeRestController {
    @Autowired
    private NoticeService noticeService;

    @Autowired

   @GetMapping("/noticelist")
    @ResponseBody
   public ModelAndView noticeList() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("views/notice/noticeList.html");
        return modelAndView;
   }

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
        NoticeSearchVO search = new NoticeSearchVO();
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
    public List<NoticeFileVO> deleteOnlyFile(@PathVariable int fileNo, @PathVariable int noticeNo) {
       this.noticeService.removeOnlyNoticeFile(fileNo);

      NoticeVO notice = this.noticeService.retrieveNotice(noticeNo);
      List<NoticeFileVO> files = notice.getNoticeFileList();
      return files;
    }

}
