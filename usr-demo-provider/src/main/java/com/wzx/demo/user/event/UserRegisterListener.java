package com.wzx.demo.user.event;

import com.wzx.demo.entity.User;
import com.wzx.demo.user.MailService;
import com.wzx.demo.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

import static com.wzx.demo.consts.UserConstant.SEND_FLAG;

/**
 * 注册发送邮件
 */
@Slf4j
@Component
public class UserRegisterListener implements ApplicationListener<RegisterEvent> {
    /**
     * @TODO 此类配置可采用配置中心统一管理
     */
    @Value("${demo.from:demo@demo.com}")
    String from = "demo@demo.com";

    @Value("${demo.title:welcome}")
    String title = "welcome";

    @Value("${demo.content:welcome}")
    String content = "welcome";

    @Resource
    MailService mailService;

    @Resource
    UserService userService;

    /**
     * 注册成功厚，发送邮件
     *
     * @param event
     * @TODO 使用event方式处理存在消息丢失问题，可使用定时任务进行补充，另外也可换用消息中间件。
     */
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        User user = userService.getUser(event.getUid());
        if (Objects.isNull(user)) {
            log.error("send email error, user is null.user:{}", event.getUid());
        }
        log.info("user register,user:{}", event.getUid());
        try {
            mailService.sendMail(from, new String[]{user.getEmail()}, title, content);
            // 邮件发送成功，修改用户邮件发送状态
            User paramUser = new User();
            paramUser.setUid(event.getUid());
            paramUser.setEmailStatus(SEND_FLAG);
            paramUser.setSendTime(new Date());
            boolean result = userService.updateUser(paramUser);
            log.info("user email update result:{},user:{}", result, event.getUid());
        } catch (Exception e) {
            log.error("user send email err,uid:{}", event.getUid());
        }
    }
}
