package com.wzx.demo.user.impl;

import com.wzx.demo.user.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.Validate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String from, String[] to, String title, String text) {
        Validate.notNull(from);
        Validate.notNull(to);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, Boolean.TRUE, CharEncoding.UTF_8);

            messageHelper.setFrom(new InternetAddress(from));
            messageHelper.setTo(to);
            messageHelper.setSubject(title);
            messageHelper.setText(text);
            mimeMessage = messageHelper.getMimeMessage();
//            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("email send error,from:{},to:{},title:{},content:{}", from, to, title, text);
            throw new RuntimeException(e);
        }
        log.info("email send success,from:{},to:{},title:{},content:{}", from, to, title, text);
    }
}
