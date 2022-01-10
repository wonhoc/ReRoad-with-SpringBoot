package com.example.member.service;

import com.example.member.vo.MailVo;

public interface MailService {
    int sendMail(MailVo mail);
}
