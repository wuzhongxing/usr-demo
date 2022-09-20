package com.wzx.demo.user.impl;

import com.wzx.demo.BaseTest;
import com.wzx.demo.user.MailService;
import org.junit.Test;

import javax.annotation.Resource;

public class MailServiceImplTest extends BaseTest {

    @Resource
    MailService mailService;

    @Test
    public void sendMailTest() {
        String from = "wuzhongxin@outlook.com";
        mailService.sendMail(from, new String[]{from}, "test", "测试");
    }
}