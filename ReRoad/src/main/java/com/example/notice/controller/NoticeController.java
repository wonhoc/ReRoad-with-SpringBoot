package com.example.notice.controller;

import com.example.common.FileVO;
import com.example.member.vo.UserAccount;
import com.example.notice.dao.NoticeDao;
import com.example.notice.service.NoticeService;
import com.example.notice.vo.NoticeVO;
import com.example.util.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeDao noticeDao;

    //파일이 저장되어있는 경로
    private String savePath = "/upload/";

    @Autowired
    private FileUploadService fileUploadService;

    //공지 목록조회 페이지로 이동
    @GetMapping("/noticelist")
    public String noticeList(Model model) {


        model.addAttribute("content", "views/notice/noticeList.html");

        return "/templates";
    }

    // 공지글 상세보기
    @GetMapping("/noticedetail/{noticeNo}")
    public String detailnotice(@PathVariable int noticeNo, Model model) {

        //조회수 증가
        this.noticeService.uphitCount(noticeNo);
        //공지글번호에 맞는 공지사항 글 조회
        NoticeVO notice = this.noticeService.retrieveNotice(noticeNo);
        model.addAttribute("notice", notice);
        model.addAttribute("content", "views/notice/noticeDetail");

        return "/templates";
    }

    // 공지글 수정
    // 수정폼불러오기
    @GetMapping("/admin/noticemodifyform/{noticeNo}")
    public String noticeModifyForm(@PathVariable int noticeNo, Model model) {

        NoticeVO notice = this.noticeService.retrieveNotice(noticeNo);
        notice.setNoticeNo(noticeNo);
        model.addAttribute("notice", notice);
        model.addAttribute("content", "views/notice/noticeModifyForm");

        return "/templates";
    }

    //공지사항 수정 처리
    @PostMapping("/admin/modifynotice")
    public String noticeModify(@Valid NoticeVO notice, @RequestParam int noticeNo, @RequestParam int[] fileNo,
                               @RequestPart(value = "noticeFileInput", required = false) List<MultipartFile> files,
                               HttpServletRequest request, Model model) {
        //수정한 공지글을 저장할 객체 생성
        NoticeVO newNotice = new NoticeVO();

        newNotice.setNoticeTitle(notice.getNoticeTitle());
        newNotice.setNoticeContent(notice.getNoticeContent());
        newNotice.setNoticeNo(noticeNo);

        //수정하며 삭제한 파일 들
        if (fileNo != null) {
            for (int deletefileNo : fileNo) {
                this.noticeDao.deleteOnlyNoticeFile(deletefileNo);
            }
        }

        //수정하며 새로 첨부한 파일을 저장할 파일리스트 생성
        List<FileVO> noticeFileVO = new ArrayList<FileVO>();

        for (MultipartFile file : files) {
            FileVO noticefile = new FileVO();
            noticefile.setFileOrigin(file.getOriginalFilename());
            String noticeFileName = null;
            if (!file.getOriginalFilename().isEmpty()) {
                noticeFileName = fileUploadService.noticeStore(file, request);
                noticefile.setFileSys(noticeFileName);
                noticefile.setFileSize(file.getSize());

                noticeFileVO.add(noticefile);
            }
        }
        newNotice.setNoticeFileList(noticeFileVO);
        this.noticeService.modifyNotice(newNotice);
        // model.addAttribute("content", "redirect:/noticelist");

        return "redirect:/noticelist";
    }

    // 공지글 작성
    // 작성폼불러오기
    @GetMapping("/admin/noticewriteform")
    public String noticeWriteForm(Model model) {
        model.addAttribute("content", "views/notice/noticeWriteForm");

        return "/templates";
    }

    //게시글 작성
    @PostMapping("/admin/writenotice")
    public String noticeWrite(@Valid NoticeVO notice,
                              @AuthenticationPrincipal User principal,
                              @RequestPart(value = "noticeFileInput", required = false) List<MultipartFile> files,
                              HttpServletRequest request, Model model) {
        //작성할 공지글 객체를 담을 새 객체 생성
        NoticeVO newNotice = new NoticeVO();
        newNotice.setNoticeTitle(notice.getNoticeTitle());
        newNotice.setNoticeContent(notice.getNoticeContent());
        newNotice.setUserId(principal.getUsername());

        List<FileVO> noticeFileVO = new ArrayList<FileVO>();

        for (MultipartFile file : files) {
            FileVO noticefile = new FileVO();
            noticefile.setFileOrigin(file.getOriginalFilename());
            String noticeFileName = null;

            //첨부 파일이 있다면,
            if (!file.getOriginalFilename().isEmpty()) {
                noticeFileName = fileUploadService.noticeStore(file, request);
                noticefile.setFileSys(noticeFileName);
                noticefile.setFileSize(file.getSize());

                noticeFileVO.add(noticefile);
            }
        }
        newNotice.setNoticeFileList(noticeFileVO);
        this.noticeService.createNotice(newNotice);
        // model.addAttribute("content", "redirect:/noticelist");

        return "redirect:/noticelist";
    }

    //파일 다운로드
    @GetMapping("/fileDownload/{fileNo}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable int fileNo) throws MalformedURLException {

        FileVO file = this.noticeService.retrieveNoticeFile(fileNo);
        String storedFileName = file.getFileSys();
        String uploadFileName = file.getFileOrigin();

        //prefix("file:") 로 프로토콜을 명시해주고 URL방식(절대경로)을 통해서 리소스의 위치를 알려줌
        UrlResource resource = new UrlResource("file:" + savePath + storedFileName);
        //파일명이 한글일 경우를 위한 인코딩
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDispostion = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDispostion).body(resource);
    }

    //공지사항 삭제
    @PostMapping("/admin/noticedelete/{noticeNo}")
    public String deleteNotice(@PathVariable int noticeNo, Model model) {
        this.noticeService.removeNotice(noticeNo);
        //   model.addAttribute("content", "redirect:/noticelist");

        return "redirect:/noticelist";
    }
}
