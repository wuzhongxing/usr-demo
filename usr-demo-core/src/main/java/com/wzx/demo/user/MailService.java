package com.wzx.demo.user;

/**
 * 邮件服务
 */
public interface MailService {
    /**
     * 发送邮件
     *
     * @param from  发件人
     * @param to    收件人
     * @param title 邮件标题
     * @param text  邮件内容
     */
    void sendMail(String from, String[] to, String title, String text);
}
