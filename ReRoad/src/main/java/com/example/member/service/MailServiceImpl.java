package com.example.member.service;


import com.example.member.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service("MailService")
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender javamailSender;
    // 메일 발송 결과 정상이면 1, 실패면 0
    int result = 0;

    @Override
    public int sendMail(MailVo mail) throws MailException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail.getAddress());
            message.setFrom("bg7006091@gmail.com");
            message.setSubject(mail.getTitle());
            message.setText(mail.getMessage());

//            javamailSender.send(message);
            result = 1;
        } catch(MailException e) {
            e.printStackTrace();
        }
        return result;
    }
}
