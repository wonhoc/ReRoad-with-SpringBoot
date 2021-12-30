package com.example.member.service;


import com.example.member.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("MailService")
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender javamailSender;

    public void setJavamailSender(JavaMailSender javamailSender) {
        this.javamailSender = javamailSender;
    }

    @Override
    public void sendMail(MailVo mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setFrom("bg7006091@gmail.com");
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        javamailSender.send(message);
    }


}
